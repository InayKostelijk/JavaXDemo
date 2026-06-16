package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class ServiceNotAvailableException extends RuntimeException {
    public ServiceNotAvailableException(String message){
        super(message);
    }
}
