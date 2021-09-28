package startproject.starbooks.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import startproject.starbooks.dto.AccountRequestDto;
import startproject.starbooks.dto.TokenRequestDto;
import startproject.starbooks.dto.TokenResponseDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
import startproject.starbooks.message.LoginMessage;
import startproject.starbooks.message.SuccessMessage;


import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    /**
     * 회원가입
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<SuccessMessage> signup(@Validated @RequestBody AccountRequestDto requestDto) {
        log.info("[registerAccount 실행 시작]");

        accountService.singup(requestDto);

        SuccessMessage successMessage = createSuccessMessage();

        log.info("[registerAccount 실행 끝]");


        //return ResponseEntity.ok(successMessage);

        return new ResponseEntity<>(successMessage, HttpStatus.OK);

    }


    /**
     * 로그인
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<LoginMessage> login(@RequestBody Map<String, String> loginAccount) {
        log.info("[login 실행 시작]");

        TokenResponseDto tokenResponseDto = accountService.login(loginAccount);

        LoginMessage loginMessage = LoginMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .tokenResponseDto(tokenResponseDto)
                .build();


        log.info("[login 실행 끝]");
        return ResponseEntity.ok(loginMessage);
    }

    /**
     * 토큰 재발급 요청
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/reissue")
    public ResponseEntity<LoginMessage> reissue(@RequestBody TokenRequestDto requestDto){
        log.info("[reissue 실행 시작]");

        log.info("[requestDto = {}]", requestDto);

        TokenResponseDto tokenResponseDto = accountService.reissue(requestDto);

        LoginMessage loginMessage = LoginMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .tokenResponseDto(tokenResponseDto)
                .build();


        log.info("[reissue 실행 끝]");
        return ResponseEntity.ok(loginMessage);
    }


    /**
     * 아이디 중복 체크
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/check/{userId}")
    public ResponseEntity<SuccessMessage> checkDuplicateUserId(@PathVariable String userId) {
        log.info("[checkDuplicateUserId 실행 시작]");

        log.info("[중복 체크 요청 Id = {}", userId);

        if (accountRepository.existsByUserId(userId)) {
            throw new ApiException(ExceptionEnum.CHECK_DUPLICATION_ERROR);
        }

        SuccessMessage successMessage = createSuccessMessage();

        log.info("[checkDuplicateUserId 실행 끝]");
        return ResponseEntity.ok(successMessage);
    }

    private SuccessMessage createSuccessMessage() {
        return SuccessMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .build();
    }
}
