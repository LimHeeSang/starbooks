package startproject.starbooks.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import startproject.starbooks.domain.account.AccountRepository;
import startproject.starbooks.domain.account.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(final ApiException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .code(e.getError().getCode())
                        .message(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(final BadCredentialsException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionEntity.builder()
                        .code("E0007")
                        .message("잘못된 비밀번호입니다.")
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .code(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .code(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionEntity.builder()
                        .code(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .message(e.getMessage())
                        .build());
    }
}