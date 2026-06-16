package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

import java.util.List;

public class CustomResponse {

    private final String message;
    private final List<FieldValidationErrorResponse> errors;

    public CustomResponse(String message, List<FieldValidationErrorResponse> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldValidationErrorResponse> getErrors() {
        return errors;
    }
}