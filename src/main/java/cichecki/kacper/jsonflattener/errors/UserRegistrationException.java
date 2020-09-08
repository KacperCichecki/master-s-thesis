package cichecki.kacper.jsonflattener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="some errors occurred during registration")
public class UserRegistrationException extends RuntimeException {


}
