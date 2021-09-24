package startproject.starbooks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import startproject.starbooks.config.JwtTokenProvider;
import startproject.starbooks.domain.Account;
import startproject.starbooks.dto.AccountRequestDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
import startproject.starbooks.message.LoginMessage;
import startproject.starbooks.message.RegisterMessage;
import startproject.starbooks.repository.AccountRepository;
import startproject.starbooks.service.AccountService;


import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/api/signup")
    public ResponseEntity registerAccount(@Validated @RequestBody AccountRequestDto requestDto) {
        log.info("[registerAccount 실행]");

        Optional<Account> findAccount = accountRepository.findByUserId(requestDto.getUserId());

        if (findAccount.isPresent()) {
            // 중복된 사용자 ID가 존재합니다.
            throw new ApiException(ExceptionEnum.DUPLICATION_ERROR);
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            // 비밀번호와 비밀번호 확인이 일치하지 않습니다.
            throw new ApiException(ExceptionEnum.DIFFERENT_PASSWORD_ERROR);
        }

        accountService.registerAccount(requestDto);

        RegisterMessage registerMessage = RegisterMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .build();


        log.info("[registerAccount 실행 끝]");
        return new ResponseEntity<>(registerMessage, HttpStatus.OK);

    }


    /**
     * 로그인
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody Map<String, String> loginAccount) {
        log.info("[login 실행]");

        // 가입된 id가 없습니다.
        Account findAccount = accountRepository.findByUserId(loginAccount.get("id")).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_REGISTER_ID));


        if (!passwordEncoder.matches(loginAccount.get("password"), findAccount.getPassword())) {
            // 잘못된 비밀번호입니다.
            throw new ApiException(ExceptionEnum.WRONG_PASSWORD);
        }

        String token = jwtTokenProvider.createToken(findAccount.getUserId(), findAccount.getRoles());

        LoginMessage loginMessage = LoginMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .token(token)
                .build();


        log.info("[login 실행 끝]");
        return new ResponseEntity<>(loginMessage, HttpStatus.OK);
    }

    /**
     * 아이디 중복 체크
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/api/check/{userId}")
    public ResponseEntity checkDuplicateUserId(@PathVariable String userId) {
        log.info("[checkDuplicateUserId 실행]");
        log.info("[중복 체크 요청 Id = {}", userId);

        if (accountRepository.existsByUserId(userId)) {
            throw new ApiException(ExceptionEnum.CHECK_DUPLICATION_ERROR);
        }

        RegisterMessage registerMessage = RegisterMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .build();


        log.info("[checkDuplicateUserId 실행]");
        return new ResponseEntity<>(registerMessage, HttpStatus.OK);
    }
}
