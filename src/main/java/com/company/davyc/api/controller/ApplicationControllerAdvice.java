package com.company.davyc.api.controller;

import com.company.davyc.api.APIErrors;
import com.company.davyc.exception.BuscaException;
import com.company.davyc.exception.ExceptionUtil;
import com.company.davyc.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ExceptionUtil{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(RegraNegocioException.class)
    public APIErrors handleRegraNegocioException(RegraNegocioException exception){
        return lidarException(exception);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(BuscaException.class)
    public APIErrors handleBuscaException(BuscaException exception){
        return lidarException(exception);

    }

}
