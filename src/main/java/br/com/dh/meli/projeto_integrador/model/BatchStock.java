package br.com.dh.meli.projeto_integrador.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStock {
    public String sectionCode;
    public String batchNumber;
    public String productId;
    public String currentTemperature;
    public String minimumTemperature;
    public String initialQuantity;
    public String currentQuantity;
    public String manufacturingDate;
    public String manufacturingTime;
    public String dueDate;
}
