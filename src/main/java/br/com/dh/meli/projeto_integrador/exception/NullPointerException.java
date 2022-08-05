package br.com.dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

public class NullPointerException extends ApiException {
    public NullPointerException(String message) {
        super(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
