package cichecki.kacper.jsonflattener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason="test error mapping")
public class CustomErrorException extends RuntimeException {}