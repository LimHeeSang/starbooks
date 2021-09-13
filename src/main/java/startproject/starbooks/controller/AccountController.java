package startproject.starbooks.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import startproject.starbooks.config.JwtTokenProvider;
import startproject.starbooks.domain.Account;
import startproject.starbooks.dto.AccountRequestDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
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
            throw new ApiException(ExceptionEnum.NOTSAME_PASSWORD_ERROR);
        }

        accountService.registerAccount(requestDto);

        log.info("[registerAccount 실행 끝]");
        return ResponseEntity.ok().build();
    }


    /**
     * 로그인
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/api/login")
    public String login(@RequestBody Map<String, String> loginAccount) {
        log.info("[login 실행]");

        Account findAccount = accountRepository.findByUserId(loginAccount.get("id")).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_REGISTER_ID));


        if (! passwordEncoder.matches(loginAccount.get("password"), findAccount.getPassword())) {
            // 잘못된 비밀번호입니다.
            throw new ApiException(ExceptionEnum.WRONG_PASSWORD);
        }

        log.info("[findAccount = {}]", findAccount);
        JsonObject obj = new JsonObject();
        obj.addProperty("token", jwtTokenProvider.createToken(findAccount.getUserId(), findAccount.getRoles()));

        log.info("[login 실행 끝]");
        return obj.toString();
    }

}
