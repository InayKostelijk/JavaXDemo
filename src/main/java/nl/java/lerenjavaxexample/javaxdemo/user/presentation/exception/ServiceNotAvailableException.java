package nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception;

public class ServiceNotAvailableException extends RuntimeException {
    public ServiceNotAvailableException(String message){
        super(message);
    }
}
