package startproject.starbooks.message;

import lombok.Builder;
import lombok.Getter;
import startproject.starbooks.dto.TokenResponseDto;

import java.util.Map;
@Getter
public class LoginMessage {

    private String code;
    private String message;
    private TokenResponseDto tokenResponseDto;

    @Builder
    public LoginMessage(String code, String message, TokenResponseDto tokenResponseDto) {
        this.code = code;
        this.message = message;
        this.tokenResponseDto = tokenResponseDto;
    }
}
