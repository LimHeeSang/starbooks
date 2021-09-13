package startproject.starbooks.exception;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiExceptionEntity {

    private String errorCode;
    private String errorMessage;

}
