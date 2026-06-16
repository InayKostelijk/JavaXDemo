package nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
