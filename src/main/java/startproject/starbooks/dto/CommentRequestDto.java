package startproject.starbooks.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;


@Getter
public class CommentRequestDto {

    private String comment;

    @Range(min=0, max=5)
    private Integer starRate;

}
