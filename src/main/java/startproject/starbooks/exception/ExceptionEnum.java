package startproject.starbooks.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"), //client error
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),  //접근 error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),  //server error 이거 뜨면 나한테 알려줘야함!


    DUPLICATION_ERROR(HttpStatus.BAD_REQUEST, "E0004", "중복된 사용자 ID가 존재합니다."),
    NOTSAME_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "E0005", "비밀번호와 비밀번호 확인이 같지 않습니다."),
    NOT_REGISTER_ID(HttpStatus.BAD_REQUEST, "E0006", "가입되지 않은 Id 입니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "E0007", "잘못된 비밀번호입니다."),

    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
