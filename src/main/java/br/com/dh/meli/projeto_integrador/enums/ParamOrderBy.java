package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ParamOrderBy {

    BATCH_NUMBER("L"),
    CURRENT_QUANTITY("Q"),
    DUE_DATE("V");

    @Getter
    String code;

    ParamOrderBy(String code) {
        this.code = code;
    }

    public static ParamOrderBy valueOfByCode(String code) {
        ParamOrderBy order = ParamOrderBy.BATCH_NUMBER;
        try {
            order = Arrays.stream(ParamOrderBy.values())
                    .filter(o -> o.getCode().equalsIgnoreCase(code))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            throw new BadRequestException("invalid orderCode");
        }
        return order;
    }
}
