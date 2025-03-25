package be.bdus.exceptions.user;

import be.bdus.exceptions.GlobalException;
import org.springframework.http.HttpStatus;

public class BadCredentialsException extends GlobalException {
    public BadCredentialsException(HttpStatus status, Object error) {
        super(status, error);
    }
}
