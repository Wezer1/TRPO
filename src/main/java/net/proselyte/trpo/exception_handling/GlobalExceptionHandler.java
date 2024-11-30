package net.proselyte.trpo.exception_handling;

import net.proselyte.trpo.exceptions.IsNotAvailableException;
import net.proselyte.trpo.exceptions.NoSuchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchException.class)
    public ResponseEntity<Object> handleNoSuchException(
            NoSuchException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(IsNotAvailableException.class)
    public ResponseEntity<Object> handleIsNotAvailableException(
            IsNotAvailableException exception){

        return ResponseEntity.status(HttpStatus.IM_USED).body(exception.getMessage());
    }


}
