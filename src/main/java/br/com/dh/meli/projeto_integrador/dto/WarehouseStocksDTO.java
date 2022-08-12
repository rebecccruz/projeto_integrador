package br.com.dh.meli.projeto_integrador.dto;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
public class WarehouseStocksDTO {
    private String productId;
    private List<WarehouseCountStocksDTO> warehouses;
}
