package progressoft.com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import progressoft.com.example.demo.model.ResponseError;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({DealNotFoundException.class})
    public ResponseEntity<ResponseError> handleDealNotFoundException(DealNotFoundException exception) {
        log.error("there is failure with deal", exception);
        ResponseError response = new ResponseError();
        response.setErrorCode(BAD_REQUEST.value());
        response.setErrorDescription(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({DuplicateDealException.class})
    public ResponseEntity<ResponseError> handleDuplicateDealException(DuplicateDealException exception) {
        log.error("there is failure with deal", exception);
        ResponseError response = new ResponseError();
        response.setErrorCode(BAD_REQUEST.value());
        response.setErrorDescription(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({InvalidCurrencyException.class})
    public ResponseEntity<ResponseError> handleInvalidCurrencyException(InvalidCurrencyException exception) {
        log.error("there is failure with deal", exception);
        ResponseError response = new ResponseError();
        response.setErrorCode(BAD_REQUEST.value());
        response.setErrorDescription(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("there is failure with request fields", exception);
        BindingResult result = exception.getBindingResult();
        ResponseError response = new ResponseError();
        response.setErrorCode(BAD_REQUEST.value());
        response.setErrorDescription(getErrorDescription(result.getFieldErrors()));
        return ResponseEntity.badRequest().body(response);
    }
    private String getErrorDescription(List<FieldError> fieldErrors) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }
        return String.join(", ", errors);
    }
}