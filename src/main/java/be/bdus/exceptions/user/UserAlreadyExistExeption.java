package be.bdus.exceptions.user;

import be.bdus.exceptions.GlobalException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistExeption extends GlobalException {
    public UserAlreadyExistExeption(HttpStatus status, Object error) {
        super(status, error);
    }
}