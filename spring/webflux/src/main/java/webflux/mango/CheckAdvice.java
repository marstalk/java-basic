package webflux.mango;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> checkBinding(WebExchangeBindException ex) {
        return new ResponseEntity<>(toStr(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> checkException(CheckException ex) {
        return new ResponseEntity<>(ex.getFieldName() + ": error value= " + ex.getFieldValue(), HttpStatus.BAD_REQUEST);
    }

    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

}
