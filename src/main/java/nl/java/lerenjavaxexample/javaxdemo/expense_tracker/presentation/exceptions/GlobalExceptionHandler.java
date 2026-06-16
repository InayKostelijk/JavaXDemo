package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException e) {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {

        List<FieldValidationErrorResponse> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new FieldValidationErrorResponse(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .collect(Collectors.toList()); // ✅ Java 8 fix

        CustomResponse response =
                new CustomResponse(
                        "1 or more fields are empty",
                        errors
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomResponse> handleResourceBadRequestException(BadRequestException ex) {

        CustomResponse error = new CustomResponse(
                ex.getMessage(),
                new ArrayList<>() // ✅ Java 8 fix
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

        CustomResponse error = new CustomResponse(
                ex.getMessage(),
                new ArrayList<>()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        CustomResponse error = new CustomResponse(
                "Invalid data operation",
                new ArrayList<>()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<CustomResponse> handeInternalServerError(InternalServerError ex) {

        CustomResponse error = new CustomResponse(
                ex.getMessage(),
                new ArrayList<>()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<CustomResponse> handeServiceNotAvailableError(ServiceNotAvailableException ex) {

        CustomResponse error = new CustomResponse(
                ex.getMessage(),
                new ArrayList<>()
        );

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<CustomResponse> handleInvalidEnum(InvalidEnumException ex) {

        return ResponseEntity.badRequest().body(
                new CustomResponse(
                        ex.getMessage(),
                        new ArrayList<>()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(
                new CustomResponse(
                        ex.getMessage(),
                        new ArrayList<>()
                )
        );
    }
}