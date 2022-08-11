package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import br.com.dh.meli.projeto_integrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    IBatchStockRepository batchStockRepo;

    @GetMapping()
    public ResponseEntity<Void> getWarehousesByProductId(
            @RequestParam(required = false) Optional<String> productId){
        if(productId.isPresent()) {
            List<CountStocks> result = batchStockRepo.countStocksByProductId(productId.get());
            result.forEach(r -> {
                System.out.println(r.getWarehouseId() + " : " + r.getQuantity());
            });
        }
        return ResponseEntity.ok().build();
    }

}
