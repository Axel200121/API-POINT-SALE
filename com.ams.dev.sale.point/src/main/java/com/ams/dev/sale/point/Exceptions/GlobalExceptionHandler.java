package com.ams.dev.sale.point.Exceptions;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto> handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        ApiResponseDto response = new ApiResponseDto();

        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            response.setStatusCode(HttpStatusCode.valueOf(401).value());
            response.setMessage("El usuario o la contraseña son incorrectos");

        } else if (exception instanceof AccessDeniedException) {
            response.setStatusCode(HttpStatusCode.valueOf(403).value());
            response.setMessage("No estás autorizado para acceder a este recurso.");

        }else if (exception instanceof SignatureException) {
            response.setStatusCode(HttpStatusCode.valueOf(403).value());
            response.setMessage("La firma JWT no es valida, verifca por favor");

        }else if (exception instanceof ExpiredJwtException) {
            response.setStatusCode(HttpStatusCode.valueOf(403).value());
            response.setMessage("La firma JWT ha expirado");
        }

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
