package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.WarehouseCountStocksDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseStocksDTO;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.service.IBatchStockService;
import br.com.dh.meli.projeto_integrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    IBatchStockService batchStockService;

    @GetMapping()
    public ResponseEntity<WarehouseStocksDTO> getWarehousesByProductId(
            @RequestParam(required = false) Optional<String> productId) {
        if(productId.isPresent()) {
            List<CountStocks> result = batchStockService.countStocksByProductId(productId.get());
            WarehouseStocksDTO dto = new WarehouseStocksDTO(productId.get(), new ArrayList());
            result.forEach(r -> {
                Warehouse warehouse = warehouseService.findWarehouseById(r.getWarehouseId());
                dto.getWarehouses().add(new WarehouseCountStocksDTO(warehouse.getCode(), r.getQuantity()));
            });
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.ok().build();
    }

}
