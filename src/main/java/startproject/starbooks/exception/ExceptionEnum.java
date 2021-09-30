package startproject.starbooks.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "Bad Request Error"), //client error
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),  //접근 error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),  //server error 이거 뜨면 나한테 알려줘야함!

    DUPLICATION_ERROR(HttpStatus.BAD_REQUEST, "E0004", "중복된 사용자 ID가 존재합니다."),
    CHECK_DUPLICATION_ERROR(HttpStatus.BAD_REQUEST, "E0009", "중복된 사용자 ID가 존재합니다."),
    DUPLICATION_EMAIL_ERROR(HttpStatus.BAD_REQUEST, "E0015", "등록된 이메일이 존재합니다"),
    DUPLICATION_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "E0016", "등록된 휴대폰 번호가 존재합니다"),

    DIFFERENT_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "E0005", "비밀번호와 비밀번호 확인이 같지 않습니다."),
    NOT_REGISTER_ID(HttpStatus.BAD_REQUEST, "E0006", "가입되지 않은 Id 입니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "E0007", "잘못된 비밀번호입니다."),
    LOGOUT_USER(HttpStatus.UNAUTHORIZED, "E0007", "로그아웃 된 사용자입니다."),

    NOT_EXIST_BOOK(HttpStatus.BAD_REQUEST, "E0009", "책이 존재하지 않습니다"),

    DUPLICATION_COMMENT(HttpStatus.BAD_REQUEST, "E0010", "댓글은 한 책당 한 번만 가능합니다."),
    NOT_EXIST_COMMENT(HttpStatus.BAD_REQUEST, "E0011", "댓글이 존재하지 않습니다."),
    STAR_ARRANGE_ERROR(HttpStatus.BAD_REQUEST, "E0012", "평점은 1~5사이만 가능합니다"),

    NOT_EXIST_HEART(HttpStatus.BAD_REQUEST, "E0013", "좋아요가 존재하지 않습니다."),
    ALREADY_EXIST_HEART(HttpStatus.BAD_REQUEST, "E0014", "이미 좋아요 상태입니다."),

    WRONG_TOKEN_SIGNED(HttpStatus.UNAUTHORIZED, "T0001", "잘못된 JWT 서명입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "T0002", "만료된 JWT 토큰입니다."),
    NOT_SUPPORT_TOKEN(HttpStatus.UNAUTHORIZED, "T0003", "지원되지 않는 JWT 토큰입니다."),
    WRONG_TOKEN_INFO(HttpStatus.UNAUTHORIZED, "T0004", "JWT 토큰이 잘못되었습니다."),

    SECURITY_01(HttpStatus.FORBIDDEN, "S0001", "권한이 없습니다."),    // 인증(토큰)은 있으나 접근권한 x
    SECURITY_02(HttpStatus.UNAUTHORIZED, "S0002", "인증이 없습니다.");


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
