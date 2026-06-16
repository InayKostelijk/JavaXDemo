package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
