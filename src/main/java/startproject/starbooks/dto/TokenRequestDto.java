package startproject.starbooks.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TokenRequestDto {

    private String accessToken;

    private String refreshToken;

}
