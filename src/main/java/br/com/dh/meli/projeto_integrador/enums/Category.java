package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import lombok.Getter;

public enum Category {

    FS("Fresh"),
    RF("Refrigerated"),
    FF("frozen");

    @Getter
    private String name;

    Category(String name) {
        this.name = name;
    }

    public static Category valueof(int categoryId) {
        if (categoryId > 2 || categoryId < 0) {
            throw new BadRequestException("invalid category");
        }
        return Category.values()[categoryId];
    }
}
