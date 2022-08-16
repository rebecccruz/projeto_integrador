package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;

public enum Status {
    FECHADO,
    ABERTO;

    public static Status getEnumName(String name) {
        try {
            return Status.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestException("Invalid status");
        }
    }

}
