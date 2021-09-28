package startproject.starbooks.exception;

import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

@Getter
public class MyAuthenticationException extends BadCredentialsException {

    private ExceptionEnum error;

    public MyAuthenticationException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }

}
