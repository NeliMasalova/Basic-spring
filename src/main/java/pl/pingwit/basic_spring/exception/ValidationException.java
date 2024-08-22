package pl.pingwit.basic_spring.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> errors;

    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "errors=" + errors +
                '}';
    }
}