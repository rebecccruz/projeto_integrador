package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseBatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseCountStocksDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseStocksDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.service.IBatchStockService;
import br.com.dh.meli.projeto_integrador.service.ISectionService;
import br.com.dh.meli.projeto_integrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Warehouse endpoints
 *
 * @author Isaias Finger, Rebecca Cruz, Evelyn Cristini Oliveira
 */
@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ISectionService sectionService;

    @Autowired
    IBatchStockService batchStockService;

    /**
     * Get Warehouses by productId
     *
     * @author Isaias Finger, Rebecca Cruz
     *
     * @param productId
     * @return ResponseEntity<WarehouseStocksDTO>
     */
    @GetMapping
    public ResponseEntity<WarehouseStocksDTO> getWarehousesByProductId(
            @RequestParam(required = true) Optional<String> productId) {
        if(productId.isPresent()) {
            List<CountStocks> result = batchStockService.countStocksByProductId(productId.get());
            WarehouseStocksDTO dto = new WarehouseStocksDTO(productId.get(), new ArrayList());
            result.forEach(r -> {
                Warehouse warehouse = warehouseService.findWarehouseById(r.getWarehouseId());
                dto.getWarehouses().add(new WarehouseCountStocksDTO(warehouse.getCode(), r.getQuantity()));
            });
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * List all BatchStocks by Warehouse and Section
     * with Optional order by L, V, Q
     *
     * @author Isaias Finger, Rebecca Cruz
     *
     * @param productId
     * @param order
     * @return ResponseEntity<List<ProductBatchStockDTO>>
     */
    @GetMapping("/list")
    public ResponseEntity<List<WarehouseBatchStockDTO>> listBatchStocksByWarehouse(
            @RequestParam(required = false) Optional<String> productId,
            @RequestParam(required = false) Optional<String> order) {
        List<WarehouseBatchStockDTO> dtos = warehouseService.getBatchStocksByFilter(productId, order);
        if(dtos.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/due-date")
    public ResponseEntity<List<BatchStockDTO>> listBatchStocksByWarehouseDueDate(
            @RequestParam(required = false) Optional<String> warehouseCode,
            @RequestParam(required = false) Optional<String> sectionCode) {
        if(warehouseCode.isEmpty()) {
            throw new BadRequestException("warehouseCode is required");
        }
        if(sectionCode.isEmpty()) {
            throw new BadRequestException("sectionCode is required");
        }
        Warehouse warehouse = warehouseService.findWarehouseByCode(warehouseCode.get());
        Section section = warehouseService.findSectionByCode(warehouse, sectionCode.get());
        List<BatchStock> batches = batchStockService.findAllBySectionOrderByDueDate(section);
        if(batches.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return ResponseEntity.ok(IBatchStockMapper.MAPPER.map(batches));
    }

    @GetMapping("/due-date/list")
    public ResponseEntity<List<BatchStockDTO>> listBatchStocksByWarehouseDueDateDays(
            @RequestParam(required = false) Optional<String> warehouseCode,
            @RequestParam(required = false) Optional<String> category,
            @RequestParam(required = false) Optional<String> days) {
        if(warehouseCode.isEmpty()) {
            throw new BadRequestException("warehouseCode is required");
        }
        Warehouse warehouse = warehouseService.findWarehouseByCode(warehouseCode.get());
        //sectionService
        sectionService.findAllByCategory();
        Section section = warehouseService.findSectionByCode(warehouse, sectionCode.get());
        List<BatchStock> batches = batchStockService.findAllBySectionOrderByDueDate(section);
        if(batches.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return ResponseEntity.ok(IBatchStockMapper.MAPPER.map(batches));
    }

}
