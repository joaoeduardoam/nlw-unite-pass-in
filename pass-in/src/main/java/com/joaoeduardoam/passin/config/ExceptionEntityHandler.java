package com.joaoeduardoam.passin.config;

import com.joaoeduardoam.passin.domain.event.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFoundException(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
