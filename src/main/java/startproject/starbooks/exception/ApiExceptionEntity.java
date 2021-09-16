package startproject.starbooks.exception;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiExceptionEntity {

    private String code;
    private String message;

}
