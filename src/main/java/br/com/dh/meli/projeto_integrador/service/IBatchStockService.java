package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.CountStocks;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Section;
import java.util.*;

public interface IBatchStockService {
    List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder);
    List<BatchStock> saveAll(List<BatchStock> batches);
    BatchStock findByProductId(String productId);
    BatchStock findByBatchNumber(Integer batchNumber);
    void batchNumberExistenceValidation(Integer batchNumber);
    List<CountStocks> countStocksByProductId(String productId);
    List<BatchStock> findAllByProductId(String productId);
    List<BatchStock> findAllByProductIdAndSection(String productId, Section section);
    List<BatchStock> findAllByProductIdOrderByBatchNumber(String productId, Section section);
    List<BatchStock> findAllByProductIdOrderByCurrentQuantity(String productId, Section section);
    List<BatchStock> findAllByProductIdOrderByDueDate(String productId, Section section);
    List<BatchStock> findAllBySectionOrderByDueDate(Section section);
}
