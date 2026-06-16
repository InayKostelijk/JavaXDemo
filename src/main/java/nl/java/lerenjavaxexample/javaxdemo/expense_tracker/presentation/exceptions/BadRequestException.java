package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
