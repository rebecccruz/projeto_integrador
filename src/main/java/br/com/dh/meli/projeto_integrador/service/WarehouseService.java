package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.WarehouseCountStocksDTO;
import br.com.dh.meli.projeto_integrador.dto.WarehouseStocksDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.*;
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

    @Override
    public WarehouseStocksDTO findStocksByProductId(String productId) {
        List<CountStocks> result = batchStockService.countStocksByProductId(productId);
        WarehouseStocksDTO dto = new WarehouseStocksDTO(productId, new ArrayList());
        result.forEach(r -> {
            Warehouse warehouse = findWarehouseById(r.getWarehouseId());
            dto.getWarehouses().add(new WarehouseCountStocksDTO(warehouse.getCode(), r.getQuantity()));
        });
        return dto;
    }

}
