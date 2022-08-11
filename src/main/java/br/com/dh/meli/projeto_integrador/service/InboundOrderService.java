package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IInboundOrderMapper;
import br.com.dh.meli.projeto_integrador.model.*;
import br.com.dh.meli.projeto_integrador.repository.IInboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private IInboundOrderRepository repo;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IBatchStockService batchStockService;

    @Override
    public InboundOrderDTO createInboundOrder(InboundOrderDTO dto) {
        Warehouse warehouse = warehouseService.findWarehouseByCode(dto.getWarehouseCode());
        Representant representant = warehouseService.findRepresentantFromWarehouse(warehouse, dto.getRepresentantId());
        Section section = findSectionByCode(warehouse, dto.getSectionCode());

        isThisBatchBelongToSection(dto.getBatchStock(), section);

        isTheSectionHasEnoughtSpace(section, dto);

        InboundOrder inboundOrder = inboundOrderMapper(dto, warehouse, representant, section);
        repo.save(inboundOrder);

        List<BatchStock> batches = batchStockService.batchStockMapper(dto.getBatchStock(), inboundOrder);
        inboundOrder.setBatchStocks(batchStockService.saveAll(batches));

        dto = IInboundOrderMapper.MAPPER.mappingInboundOrderToInboundOrderDTO(inboundOrder);
        return dto;
    }

    @Override
    public InboundOrderDTO updateInboundOrder(InboundOrderDTO dto) {
        // TODO: Update inboundOrder from dto
        return dto;
    }

    private Section findSectionByCode(Warehouse warehouse, String code) {
        Optional<Section> section = warehouse.getSections()
                .stream().filter(s -> s.getCode().equalsIgnoreCase(code)).findFirst();
        if (section.isEmpty()) {
            throw new BadRequestException("invalid sectionCode");
        }
        return section.get();
    }

    private void isThisBatchBelongToSection(List<BatchStockDTO> batches, Section section) {
        batches.stream().forEach(batch -> isThisBatchBelongToSection(batch, section));
    }

    private void isThisBatchBelongToSection(BatchStockDTO batch, Section section) {
        Float maximumTemperature = section.getCategory().getMaximumTemperature();
        Float minimumTemperature = section.getCategory().getMinimumTemperature();
        Float batchCurrentTemperature = batch.getCurrentTemperature();

        if (batchCurrentTemperature > maximumTemperature || batchCurrentTemperature < minimumTemperature) {
            throw new BadRequestException("this batch doesn't belong to the section.");
        }
    }

    private void isTheSectionHasEnoughtSpace(Section section, InboundOrderDTO inbound) {
        int maxCapacity = section.getCapacity();
        int currentCapacity = section.getBatchStocks().size();
        int availableCapacity = maxCapacity - currentCapacity;
        int neededCapacity = inbound.getBatchStock().size();
        boolean dontHaveCapacity = availableCapacity < neededCapacity;
        if (dontHaveCapacity) {
            throw new PreconditionFailedException("this section don't have enought space.");
        }
    }

    private InboundOrder inboundOrderMapper(InboundOrderDTO dto, Warehouse warehouse,
                                            Representant representant, Section section) {
        InboundOrder inboundOrder = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(dto);
        inboundOrder.setWarehouse(warehouse);
        inboundOrder.setRepresentant(representant);
        inboundOrder.setSection(section);
        return inboundOrder;
    }
}
