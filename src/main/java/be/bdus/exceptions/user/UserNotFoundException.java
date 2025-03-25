package be.bdus.exceptions.user;

import be.bdus.exceptions.GlobalException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}
