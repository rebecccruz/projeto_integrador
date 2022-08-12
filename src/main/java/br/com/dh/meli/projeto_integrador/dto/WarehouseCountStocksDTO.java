package br.com.dh.meli.projeto_integrador.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class WarehouseCountStocksDTO {
    private String warehouseCode;
    private Integer totalQuantity;
}
