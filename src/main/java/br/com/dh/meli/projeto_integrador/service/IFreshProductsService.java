package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Product;

import java.util.List;
import java.util.Optional;

public interface IFreshProductsService {
    List<BatchStockDTO> getAllBatchStock();
    List<BatchStockDTO> getAllBatchStockByCategory(Optional<Category> category);

    BatchStockDTO createBatchStock(BatchStockDTO batchStock);

}
