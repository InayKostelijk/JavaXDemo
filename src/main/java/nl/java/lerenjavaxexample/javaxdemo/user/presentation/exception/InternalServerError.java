package nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
