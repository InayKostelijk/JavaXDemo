package nl.java.lerenjavaxexample.javaxdemo.user.presentation.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
//https://www.geeksforgeeks.org/springboot/exception-handling-in-spring-boot/

@ControllerAdvice(basePackages = "nl.confighurator.backend.user")
public class UserGlobalExceptionHandler {
//https://stackoverflow.com/questions/62220899/display-only-default-message-of-spring-boot-validation-methodargumentnotvalide/71910867
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        CustomResponse<Map<String, String>> msg = new CustomResponse<>(
                HttpStatus.BAD_REQUEST,
                errorMap.toString()

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomResponse<String>> handleResourceBadRequestException(BadRequestException ex) {
        CustomResponse<String > error = new CustomResponse<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        CustomResponse<String > error = new CustomResponse<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage()

        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //https://www.baeldung.com/spring-dataintegrityviolationexception
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponse<String>> handleDataIntegrityViolation(org.springframework.dao.DataIntegrityViolationException ex) {
        CustomResponse<String> error = new CustomResponse<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<CustomResponse<String>> handeInternalServerError(InternalServerError ex){
        CustomResponse<String > error = new CustomResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<CustomResponse<String>> handeServiceNotAvailableError(ServiceNotAvailableException ex){
        CustomResponse<String > error = new CustomResponse<>(
                HttpStatus.SERVICE_UNAVAILABLE,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<CustomResponse<String>> handleInvalidEnum(InvalidEnumException ex) {
        return ResponseEntity.badRequest().body(
                new CustomResponse<>(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<CustomResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(
                new CustomResponse<>(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                )
        );
    }
}


