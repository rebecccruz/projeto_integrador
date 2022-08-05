package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {
    public String batchNumber;
    public String productId;
    public String currentTemperature;
    public String minimumTemperature;
    public String initialQuantity;
    public String currentQuantity;
    public String manufacturingDate;
    public String manufacturingTime;
    public String dueDate;

    public BatchStockDTO(BatchStock batchStock) {
        this.batchNumber = batchStock.getBatchNumber();
        this.productId = batchStock.getProductId();
    }
}
