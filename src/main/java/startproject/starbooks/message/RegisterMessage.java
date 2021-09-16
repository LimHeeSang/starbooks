package startproject.starbooks.message;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterMessage {

    private String code;
    private String message;

    @Builder
    public RegisterMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
