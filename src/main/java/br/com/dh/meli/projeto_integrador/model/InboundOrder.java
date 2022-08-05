package br.com.dh.meli.projeto_integrador.model;

import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrder {
    public String orderNumber;
    public String orderDate;
    public String warehouseCode;
    public List<BatchStock> batchStock;
}
