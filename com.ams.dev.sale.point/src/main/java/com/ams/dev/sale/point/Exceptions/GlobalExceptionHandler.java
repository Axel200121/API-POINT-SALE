package com.ams.dev.sale.point.Exceptions;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto> handleException(Exception exception) {

        exception.printStackTrace();
        ApiResponseDto apiResponse = new ApiResponseDto();


        if (exception instanceof IllegalArgumentException) {
            apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
            apiResponse.setMessage("EL NOMBRE ROL NO EXISTE");
            apiResponse.setData(new Object());
        }


        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatusCode()));

    }
}
