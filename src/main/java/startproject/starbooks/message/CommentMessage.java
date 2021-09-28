package startproject.starbooks.message;

import lombok.Builder;
import lombok.Getter;
import startproject.starbooks.dto.TokenResponseDto;

import java.util.Map;

@Getter
public class CommentMessage {

    private String code;
    private String message;
    private Map<String, Object> commentMap;

    @Builder
    public CommentMessage(String code, String message, Map<String, Object> commentMap) {
        this.code = code;
        this.message = message;
        this.commentMap = commentMap;
    }}
