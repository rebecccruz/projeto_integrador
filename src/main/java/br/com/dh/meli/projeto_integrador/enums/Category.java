package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import lombok.Getter;

public enum Category {

    FS("Fresh", 5.0F, 25.0F),
    RF("Refrigerated", 5.0F, -18.0F),
    FF("Frozen", -18.0F, -40.0F);

    @Getter
    private String name;
    public Float minimumTemperature;
    public Float maximumTemperature;

    Category(String name, Float minimumTemperature, Float maximumTemperature) {
        this.name = name;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
    }

    public static Category valueof(int categoryId) {
        if (categoryId > 2 || categoryId < 0) {
            throw new BadRequestException("invalid category");
        }
        return Category.values()[categoryId];
    }
}
