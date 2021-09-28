package startproject.starbooks.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;


@Getter
public class CommentRequestDto {

    private String comment;

    @Range(min=1, max=5, message = "평점은 1~5사이만 가능합니다.")
    private Integer starRate;

}
