package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class FieldValidationErrorResponse {

    private final String field;
    private final String message;

    public FieldValidationErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}