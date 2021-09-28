package startproject.starbooks.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDto {

    private Long commentId;

    private String userId;

    private String comment;

    private int starRate;

}
