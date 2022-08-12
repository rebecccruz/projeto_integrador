package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private Advertisement advertisement;
    private BatchStock batchStock;
    private Integer quantity;
}
