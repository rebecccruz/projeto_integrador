package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import lombok.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class InboundOrderDTO {
    public List<BatchStockDTO> batchStock;

    public InboundOrderDTO(InboundOrder inboundOrder) {
        List<BatchStockDTO> list = inboundOrder.getBatchStock()
                .stream().map(BatchStockDTO::new).collect(Collectors.toList());
        this.setBatchStock(list);
    }
}
