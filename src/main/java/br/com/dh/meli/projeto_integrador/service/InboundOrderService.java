package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.mapper.IInboundOrderMapper;
import br.com.dh.meli.projeto_integrador.model.*;
import br.com.dh.meli.projeto_integrador.repository.IInboundOrderRepository;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import br.com.dh.meli.projeto_integrador.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private IInboundOrderRepository repo;
    @Autowired
    private IWarehouseRepository warehouseRepo;
    @Autowired
    private ISectionRepository sectionRepo;

    @Override
    public InboundOrderDTO createInboundOrder(InboundOrderDTO dto) {
        Warehouse warehouse = findWarehouseByCode(dto.getWarehouseCode());
        Representant representant = findRepresentantFromWarehouse(warehouse, dto.getRepresentantId());
        Section section = findSectionByCode(warehouse, dto.getSectionCode());

        // TODO: E que o setor corresponde ao tipo de produto

        // TODO: E que o setor tenha espaço disponível

        int maxCapacity = section.getCapacity();
        int currentCapacity = section.getBatchStocks().size();
        int availableCapacity = maxCapacity - currentCapacity;
        int neededCapacity = dto.getBatchStock().size();
        boolean haveCapacity = availableCapacity >= neededCapacity;
        if (haveCapacity) {
            InboundOrder inboundOrder = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(dto);
            // TODO: Save in database
            //repo.save(inboundOrder);
        }
        return dto;
    }

    @Override
    public InboundOrderDTO updateInboundOrder(InboundOrderDTO dto) {
        // TODO: Update inboundOrder from dto
        return dto;
    }

    private void saveBatchStock(BatchStock batchStock) {

    }

    private Warehouse findWarehouseByCode(String code) {
        Optional<Warehouse> warehouse = warehouseRepo.findWarehouseByCode(code);
        if (warehouse.isEmpty()) {
            throw new BadRequestException("invalid warehouseCode");
        }
        return warehouse.get();
    }

    private Representant findRepresentantFromWarehouse(Warehouse warehouse, Long id) {
        Optional<Representant> representant = warehouse.getRepresentants()
                .stream().filter(r -> r.getId().equals(id)).findFirst();
        if (representant.isEmpty()) {
            throw new BadRequestException("invalid representId");
        }
        return representant.get();
    }

    private Section findSectionByCode(Warehouse warehouse, String code)
    {
        Optional<Section> section = warehouse.getSections()
                .stream().filter(s -> s.getCode().equalsIgnoreCase(code)).findFirst();
        if (section.isEmpty()) {
            throw new BadRequestException("invalid sectionCode");
        }
        return section.get();
    }
}
