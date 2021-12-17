package me.swim.currencycalc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"message\" : \""+e.getMessage()+"\"}");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullPointerExceptionHandler(NullPointerException e){
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"message\" : \""+e.getMessage()+"\"}");
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindExceptionHandler(BindException e){
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"message\" : \""+e.getMessage()+"\"}");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body("{\"message\" : \""+e.getMessage()+"\"}");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e){
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body("{\"message\" : \""+e.getMessage()+"\"}");
    }
}
