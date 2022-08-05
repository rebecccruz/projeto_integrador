package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import lombok.*;
import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {
    public String sectionCode;
    public int batchNumber;
    public String productId;
    public Float currentTemperature;
    public Float minimumTemperature;
    public int initialQuantity;
    public int currentQuantity;
    public LocalDate manufacturingDate;
    public LocalDateTime manufacturingTime;
    public LocalDate dueDate;

    public BatchStockDTO(BatchStock batchStock) {
        this.batchNumber = batchStock.getBatchNumber();
        this.productId = batchStock.getProductId();
    }
}
