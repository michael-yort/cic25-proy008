package es.cic.curso25.proy008.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proy008.exception.ModificationSecurityException;

@RestControllerAdvice
public class MyControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ModificationSecurityException.class)

    public void controloModificacionNoPermitida() {

    }
}