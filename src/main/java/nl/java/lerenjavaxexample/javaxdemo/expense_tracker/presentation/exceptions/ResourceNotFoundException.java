package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
