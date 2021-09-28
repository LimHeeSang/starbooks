package startproject.starbooks.message;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class HeartMessage {

    private String code;
    private String message;
    private Map<String, Object> map;

    @Builder
    public HeartMessage(String code, String message, Map<String, Object> map) {
        this.code = code;
        this.message = message;
        this.map = map;
    }

}
