package br.com.dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

public class PreconditionFailedException extends ApiException {
    public PreconditionFailedException(String message) {
        super(message);
        this.setStatus(HttpStatus.PRECONDITION_FAILED);
    }
}
