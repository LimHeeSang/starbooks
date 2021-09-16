package startproject.starbooks.message;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginMessage {

    private String code;
    private String message;
    private String token;

    @Builder
    public LoginMessage(String code, String message, String token) {
        this.code = code;
        this.message = message;
        this.token = token;
    }
}
