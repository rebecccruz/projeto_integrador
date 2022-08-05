package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for ml-insert-batch-in-fulfillment-warehouse
 */
@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    IInboundOrderService service;

    /**
     * Cadastre um lote com o estoque de produtos que o compõe.
     * Devolva o lote criado com o código de status "201 CREATED".
     *
     * @param inboundOrder
     * @return List<BatchStockDTO>
     */
    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrderDTO> createBatchStock(@RequestBody InboundOrder inboundOrder) {
        InboundOrderDTO dto = new InboundOrderDTO(inboundOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * Caso o lote já exista e deva ser atualizado.
     * Devolva o estoque atualizado com o código de status "201 CREATED".
     *
     * @param inboundOrder
     * @return List<BatchStockDTO>
     */
    @PutMapping("/inboundorder")
    public ResponseEntity<InboundOrderDTO> updateBatchStock(@RequestBody InboundOrder inboundOrder) {
        InboundOrderDTO dto = new InboundOrderDTO(inboundOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
