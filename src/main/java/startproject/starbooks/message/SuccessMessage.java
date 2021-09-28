package startproject.starbooks.message;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class SuccessMessage {

    private String code;
    private String message;

    @Builder
    public SuccessMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
