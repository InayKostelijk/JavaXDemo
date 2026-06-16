package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

public class InvalidEnumException extends RuntimeException {
  public InvalidEnumException(String message) {
    super(message);
  }
}
