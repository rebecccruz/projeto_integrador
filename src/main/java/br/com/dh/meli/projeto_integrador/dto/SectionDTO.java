package br.com.dh.meli.projeto_integrador.dto;

import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
    private String warehouseCode;
    private String sectionCode;
    private String productId;
    private List<BatchStockDTO> batchStocks;
}
