package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockService implements IBatchStockService {

    @Autowired
    private IBatchStockRepository repo;

    public List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder) {
        return batches.stream().map(dto -> {
            BatchStock batch = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(dto);
            batch.setInboundOrder(inboundOrder);
            batch.setSection(inboundOrder.getSection());
            return batch;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BatchStock> saveAll(List<BatchStock> batches) {
        return repo.saveAll(batches);
    }

    @Override
    public BatchStock findByProductId(String productId) {
        Optional<BatchStock> batchStock = repo.findBatchStockByProductId(productId);
        if(batchStock.isEmpty()) {
            throw new NotFoundException("productId not found");
        }
        return batchStock.get();
    }

    @Override
    public BatchStock findByBatchNumber(Integer batchNumber) {
        Optional<BatchStock> batchFound = repo.findBatchStockByBatchNumber(batchNumber);
        if (batchFound.isEmpty()) {
            throw new PreconditionFailedException("batch doesn't exists");
        }
        return batchFound.get();
    }

    @Override
    public void batchNumberExistenceValidation(Integer batchNumber) {
        Optional<BatchStock> batchFound = repo.findBatchStockByBatchNumber(batchNumber);
        if (batchFound.isPresent()) {
            throw new PreconditionFailedException("batch already exists");
        }
    }

    @Override
    public List<CountStocks> countStocksByProductId(String productId) {
         if(!repo.existsBatchStocksByProductId(productId))
         {
             throw new NotFoundException("productId not found");
         }
         return repo.countStocksByProductId(productId);
    }

    @Override
    public List<BatchStock> findAllByProductId(String productId) {
        return repo.findBatchStocksByProductId(productId);
    }

    @Override
    public List<BatchStock> findAllByProductIdAndSection(String productId, Section section) {
        return repo.findBatchStocksByProductIdAndSection(productId, section);
    }

    @Override
    public List<BatchStock> findAllByProductIdOrderByBatchNumber(String productId, Section section) {
        return repo.findBatchStocksByProductIdAndSectionOrderByBatchNumberAsc(productId, section);
    }

    @Override
    public List<BatchStock> findAllByProductIdOrderByCurrentQuantity(String productId, Section section) {
        return repo.findBatchStocksByProductIdAndSectionOrderByCurrentQuantityAsc(productId, section);
    }

    @Override
    public List<BatchStock> findAllByProductIdOrderByDueDate(String productId, Section section) {
        return repo.findBatchStocksByProductIdAndSectionOrderByDueDateAsc(productId, section);
    }

    @Override
    public List<BatchStock> findAllBySectionOrderByDueDate(Section section) {
        return repo.findBatchStocksBySectionOrderByDueDateAsc(section);
    }
}
