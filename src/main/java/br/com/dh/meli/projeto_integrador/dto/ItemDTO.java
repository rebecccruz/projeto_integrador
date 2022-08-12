package br.com.dh.meli.projeto_integrador.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private Long advertisementId;
    private Integer batchStockId;
    private Integer quantity;
}
