package br.com.dh.meli.projeto_integrador.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.*;

@Data
@Builder
public class BatchStock {
    public int batchNumber;
    public String productId;
    public Float currentTemperature;
    public Float minimumTemperature;
    public int initialQuantity;
    public int currentQuantity;
    public LocalDate manufacturingDate;
    public LocalDateTime manufacturingTime;
    public LocalDate dueDate;
}
