package se.alexjons987.item_registry.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // Generic exceptions
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();

        body.put("error", "Unexpected error: " + ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Validation exceptions
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> error.put(err.getField(), err.getDefaultMessage()));

        body.put("errors", error);

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class) // Username already in use/taken
    public ResponseEntity<Map<String, Object>> handleUsernameTaken(UsernameAlreadyTakenException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(AuthenticationException.class) // Authentication exception i.e. Bad credentials
    public ResponseEntity<Map<String, Object>> handleAuthException(AuthenticationException ex) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(ForbiddenOperationException.class) // Authentication exception i.e. Bad credentials
    public ResponseEntity<Map<String, Object>> handleForbiddenOperationException(ForbiddenOperationException ex) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(ItemNotFoundException.class) // Item not found
    public ResponseEntity<Map<String, Object>> handleItemNotFoundException(ItemNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }
}

