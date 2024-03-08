package progressoft.com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({DealNotFoundException.class})
    public ResponseEntity<?> handleDealNotFoundException(DealNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({DuplicateDealException.class})
    public ResponseEntity<?> handleDuplicateDealException(DuplicateDealException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({InvalidCurrencyException.class})
    public ResponseEntity<?> handleInvalidCurrencyException(InvalidCurrencyException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
