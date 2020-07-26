package cichecki.kacper.jsonflattener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason="This method has not been implemented yet")
public class NotImplementedException extends RuntimeException {}