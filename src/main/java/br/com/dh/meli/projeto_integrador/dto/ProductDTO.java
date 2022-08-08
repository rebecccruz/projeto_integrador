package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer batchNumber;
    private Float currentTemperature;
    private Float minimumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Category category;

    public ProductDTO(Product product) {
        this.batchNumber = product.getBatchNumber();
        this.currentTemperature = product.getCurrentTemperature();
        this.minimumTemperature = product.getMinimumTemperature();
        this.initialQuantity = product.getInitialQuantity();
        this.currentQuantity = product.getCurrentQuantity();
        this.manufacturingDate = product.getManufaturingDate();
        this.manufacturingTime = product.getManufaturingTime();
        this.dueDate = product.getDueDate();
        this.category = product.getCategory();
    }
}
