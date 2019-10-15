package com.akvasoft.construction.config;


import com.akvasoft.construction.dto.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> invalidInput(MethodArgumentNotValidException ex) {
        Response response=new Response();
        response.setSuccess(false);
        response.setErrorMessage("Please recheck data-sent. They are not correct or doesn't contain all the fields required");
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
