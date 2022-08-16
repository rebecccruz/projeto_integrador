package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
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
    public InboundOrder createInboundOrder(InboundOrderDTO dto) {
        orderNumberExistenceValidation(dto.getOrderNumber());
        InboundOrder inboundOrder = inboundOrderPipelineValidation(dto);

        List<BatchStock> batches = batchStockService.batchStockMapper(dto.getBatchStock(), inboundOrder);
        checkIfBatchesExists(batches);

        repo.save(inboundOrder);
        batchStockService.saveAll(batches);
        inboundOrder.setBatchStocks(batches);

        return inboundOrder;
    }

    public InboundOrderDTO convertToDto(InboundOrder inboundOrder)
    {
        InboundOrderDTO dto = IInboundOrderMapper.MAPPER.mappingInboundOrderToInboundOrderDTO(inboundOrder);
        dto.setWarehouseCode(inboundOrder.getWarehouse().getCode());
        dto.setSectionCode(inboundOrder.getSection().getCode());
        dto.setRepresentantId(inboundOrder.getRepresentant().getId());
        dto.setBatchStock(IBatchStockMapper.MAPPER.map(inboundOrder.getBatchStocks()));
        return dto;
    }

    @Override
    public InboundOrder updateInboundOrder(InboundOrderDTO dto) {
        InboundOrder inboundOrder = findByOrderNumber(dto.getOrderNumber());
        inboundOrder = inboundOrderPipelineValidation(dto);

        List<BatchStock> batches = batchStockService.batchStockMapper(dto.getBatchStock(), inboundOrder);
        checkIfBatchesDoesNotExists(batches);

        repo.save(inboundOrder);
        batchStockService.saveAll(batches);

        inboundOrder.setBatchStocks(batches);
        return inboundOrder;
    }

    private InboundOrder inboundOrderPipelineValidation(InboundOrderDTO dto){
        Warehouse warehouse = warehouseService.findWarehouseByCode(dto.getWarehouseCode());
        Representant representant = warehouseService.findRepresentantFromWarehouse(warehouse, dto.getRepresentantId());
        Section section = findSectionByCode(warehouse, dto.getSectionCode());

        isThisBatchBelongToSection(dto.getBatchStock(), section);

        isTheSectionHasEnoughtSpace(section, dto);

        InboundOrder inboundOrder = inboundOrderMapper(dto, warehouse, representant, section);
        return inboundOrder;
    }

    private void checkIfBatchesExists(List<BatchStock> batches){
        batches.forEach(batch -> batchStockService.batchNumberExistenceValidation(batch.getBatchNumber()));
    }

    private void checkIfBatchesDoesNotExists(List<BatchStock> batches) {
        batches.forEach(batch -> batchStockService.findByBatchNumber(batch.getBatchNumber()));
    }

    private InboundOrder findByOrderNumber(Integer orderNumber) {
        Optional<InboundOrder> inboundOrder = repo.findInboundOrderByOrderNumber(orderNumber);
        if(inboundOrder.isEmpty()) {
            throw new NotFoundException("orderNumber not found");
        }
        return inboundOrder.get();
    }

    private void orderNumberExistenceValidation(Integer orderNumber) {
        Optional<InboundOrder> inboundOrder = repo.findInboundOrderByOrderNumber(orderNumber);
        if(inboundOrder.isPresent()) {
            throw new PreconditionFailedException("orderNumber already exists");
        }
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
