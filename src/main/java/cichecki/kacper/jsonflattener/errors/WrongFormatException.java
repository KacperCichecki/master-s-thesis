package cichecki.kacper.jsonflattener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Wrong format has been provided")
public class WrongFormatException extends RuntimeException{
    public WrongFormatException(final String message) {
        super(message);
    }
}
