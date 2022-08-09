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
        Optional<Warehouse> warehouse = warehouseRepo.findWarehouseByCode(dto.getWarehouseCode());

        // E que o armazém é válido
        if (warehouse.isEmpty()) {
            throw new BadRequestException("invalid wareHouseCode");
        }

        // E que o representante pertence ao armazém
        Optional<Representant> representant = warehouse.get().getRepresentants()
                .stream().filter(r -> r.getId().equals(dto.getRepresentantId())).findFirst();
        if (representant.isEmpty()) {
            throw new BadRequestException("invalid representId");
        }

        // E que o setor é válido
        Optional<Section> section = warehouse.get().getSections()
                .stream().filter(s -> s.getCode().equalsIgnoreCase(dto.getSectionCode())).findFirst();
        if (section.isEmpty()) {
            throw new BadRequestException("invalid sectionCode");
        }

        // TODO: E que o setor corresponde ao tipo de produto

        // TODO: E que o setor tenha espaço disponível
        int maxCapacity = section.get().getCapacity();
        int currentCapacity = section.get().getBatchStocks().size();
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
}
