package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.WarehouseStocksDTO;
import br.com.dh.meli.projeto_integrador.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * Warehouse endpoints
 *
 * @author Isaias Finger, Rebecca Cruz, Evelyn Cristini Oliveira
 */
@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    IWarehouseService service;

    /**
     * Find stocks of ProductId
     *
     * @param productId
     * @return ResponseEntity<WarehouseStocksDTO>
     * @author Isaias Finger, Rebecca Cruz
     */
    @GetMapping("/{productId}")
    public ResponseEntity<WarehouseStocksDTO> findStocksByProductId(
            @PathVariable @Valid @NotEmpty String productId) {
        return ResponseEntity.ok(service.findStocksByProductId(productId));
    }

}
