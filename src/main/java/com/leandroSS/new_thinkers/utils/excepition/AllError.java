package com.leandroSS.new_thinkers.utils.excepition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AllError extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErroResponse> NotFoundErroHandler(CustomException exception) {
        ErroResponse threartResponse = new ErroResponse(404, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threartResponse);
    }
}