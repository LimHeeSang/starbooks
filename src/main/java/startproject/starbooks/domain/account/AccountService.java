package startproject.starbooks.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import startproject.starbooks.domain.refresh.RefreshToken;
import startproject.starbooks.domain.refresh.RefreshTokenRepository;
import startproject.starbooks.dto.AccountRequestDto;
import startproject.starbooks.dto.TokenRequestDto;
import startproject.starbooks.dto.TokenResponseDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
import startproject.starbooks.security.JwtTokenProvider;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Transactional
    public void singup(AccountRequestDto requestDto) {

        Optional<Account> findAccount = accountRepository.findByUserId(requestDto.getUserId());

        if (findAccount.isPresent()) {
            // 중복된 사용자 ID가 존재합니다.
            throw new ApiException(ExceptionEnum.DUPLICATION_ERROR);
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            // 비밀번호와 비밀번호 확인이 일치하지 않습니다.
            throw new ApiException(ExceptionEnum.DIFFERENT_PASSWORD_ERROR);
        }

        Account ac = Account.builder()
                .userName(requestDto.getUserName())
                .userId(requestDto.getUserId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .birthDate(requestDto.getBirthDate())
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .userRole(UserRole.ROLE_USER)
                .build();

        accountRepository.save(ac);

    }

    @Transactional
    public TokenResponseDto login(Map<String, String> loginAccount) {

        Account checkAccount = accountRepository.findByUserId(loginAccount.get("id")).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        // 1. login id/pw를 기반으로 authenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginAccount.get("id"), loginAccount.get("password"));

        // 2. 실제로 검증(사용자 아이디와 비밀번호 체크)이 이루어지는 부분
        // authenticate 메소드가 실행이 될 때 UserDetailsServiceImpl에서 만들었던 loadUserByUsername 메소드가 실행됨\
        // + 비밀번호가 틀렸을 상황의 예외처리
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


        // 3. 인증객체를 기반으로 Jwt 토큰 생성
        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateTokenDto(authentication);

        // 4. refreshToken 저장
        Account findAccount = accountRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        RefreshToken refreshToken = findAccount.setRefreshToken(tokenResponseDto.getRefreshToken());

        refreshTokenRepository.save(refreshToken);

        return tokenResponseDto;
    }

    @Transactional
    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // 2. Access Token에서 Member Id 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member Id를 기반으로 Refresh Token 가져오기
        Account findAccount = accountRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new UsernameNotFoundException("등록된 Id가 없습니다.")
        );

        RefreshToken refreshToken = findAccount.getRefreshToken().orElseThrow(
                () -> new ApiException(ExceptionEnum.LOGOUT_USER)
        );


        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRefreshValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenResponseDto tokenResponseDto = jwtTokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenResponseDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenResponseDto;
    }

}
