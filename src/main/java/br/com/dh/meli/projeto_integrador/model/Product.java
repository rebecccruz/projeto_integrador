package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.enums.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Product {
    private String productId;
    private Integer batchNumber;
    private Float currentTemperature;
    private Float minimumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Category category;
}
