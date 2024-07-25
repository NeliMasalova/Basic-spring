package pl.pingwit.basic_spring.config.exeptionhadling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pingwit.basic_spring.exception.ValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class PingwitRestExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.toString());
    }
}