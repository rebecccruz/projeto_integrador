package br.com.dh.meli.projeto_integrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

@Data
@Entity
@Builder
public class BatchStock {
    @Id
    @Column(name = "id")
    public Integer batchNumber;

    @Column(name = "product_sku", nullable = false)
    public String productId;

    @Column(name = "current_temperature", nullable = false)
    public Float currentTemperature;

    @Column(name = "minimum_temperature", nullable = false)
    public Float minimumTemperature;

    @Column(name = "initial_quantity", nullable = false)
    public Integer initialQuantity;

    @Column(name = "current_quantity", nullable = false)
    public Integer currentQuantity;

    @Column(name = "manufacturing_date", nullable = false)
    public LocalDate manufacturingDate;

    @Column(name = "manufacturing_time", nullable = false)
    public LocalDateTime manufacturingTime;

    @Column(name = "due_date", nullable = false)
    public LocalDate dueDate;
}
