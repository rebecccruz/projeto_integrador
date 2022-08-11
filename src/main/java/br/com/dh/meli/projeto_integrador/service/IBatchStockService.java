package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;

import java.time.LocalDate;
import java.util.*;

public interface IBatchStockService {
    List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder);
    List<BatchStock> saveAll(List<BatchStock> batches);
    BatchStock findByProductId(String productId);
}
