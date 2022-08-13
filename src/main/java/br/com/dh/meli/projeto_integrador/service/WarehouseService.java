package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseBatchStockDTO;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private IWarehouseRepository repo;

    @Autowired
    private IBatchStockService batchStockService;

    @Override
    public Warehouse findWarehouseById(Long id) {
        Optional<Warehouse> warehouse = repo.findById(id);
        if (warehouse.isEmpty()) {
            throw new NotFoundException("WarehouseId not found");
        }
        return warehouse.get();
    }

    @Override
    public Warehouse findWarehouseByCode(String code) {
        Optional<Warehouse> warehouse = repo.findWarehouseByCode(code);
        if (warehouse.isEmpty()) {
            throw new PreconditionFailedException("Warehouse code not found");
        }
        return warehouse.get();
    }

    @Override
    public Representant findRepresentantFromWarehouse(Warehouse warehouse, Long id) {
        Optional<Representant> representant = warehouse.getRepresentants()
                .stream().filter(r -> r.getId().equals(id)).findFirst();
        if (representant.isEmpty()) {
            throw new BadRequestException("invalid representId");
        }
        return representant.get();
    }

    public List<WarehouseBatchStockDTO> getBatchStocksByFilter(
            Optional<String> productId,
            Optional<String> order
    ) {
        List<BatchStock> allBatches = batchStockService.findAllByProductId(productId.get());
        List<WarehouseBatchStockDTO> dtos = new ArrayList<>();
        allBatches.forEach(b -> {
            boolean process = dtos.stream().anyMatch(stock -> stock.getSectionCode().equalsIgnoreCase(b.getSection().getCode()));
            if (!process) {
                WarehouseBatchStockDTO dto = new WarehouseBatchStockDTO();
                dto.setWarehouseCode(b.getSection().getWarehouse().getCode());
                dto.setSectionCode(b.getSection().getCode());
                dto.setProductId(b.getProductId());
                List<BatchStockDTO> batchesDto = IBatchStockMapper.MAPPER.map(
                        batchStockService.findAllByProductIdAndSection(b.getProductId(), b.getSection()));
                if (order.isPresent()) {
                    batchesDto = IBatchStockMapper.MAPPER.map(
                            getAllByOrder(b.getProductId(), b.getSection(), ParamOrderBy.valueOfByCode(order.get())));
                }
                dto.setBatchStocks(batchesDto);
                dtos.add(dto);
            }
        });
        return dtos;
    }

    private List<BatchStock> getAllByOrder(String productId, Section section, ParamOrderBy orderBy) {
        switch (orderBy) {
            case BATCH_NUMBER:
                return batchStockService.findAllByProductIdOrderByBatchNumber(productId, section);
            case CURRENT_QUANTITY:
                return batchStockService.findAllByProductIdOrderByCurrentQuantity(productId, section);
            case DUE_DATE:
                return batchStockService.findAllByProductIdOrderByDueDate(productId, section);
            default:
                throw new BadRequestException("invalid orderId");
        }
    }

}
