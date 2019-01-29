package com.bamboovir.muresearchboost.app.exception;

import com.bamboovir.muresearchboost.model.SecurityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bamboovir.muresearchboost.app.exception.CustomSecurityException;

@ControllerAdvice
public class SecurityController {
    @ExceptionHandler(CustomSecurityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SecurityResponse handleSecurityException(CustomSecurityException se) {
        return new SecurityResponse(se.getMessage());
    }
}
