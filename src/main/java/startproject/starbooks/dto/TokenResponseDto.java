package startproject.starbooks.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponseDto {

    private String grantType;

    private Long accessTokenExpiresIn;

    private String accessToken;

    private String refreshToken;

}
