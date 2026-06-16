package nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception;

import org.springframework.http.HttpStatus;

public class CustomResponse<T> {

    private HttpStatus status;
    private String message;


    public CustomResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    }

