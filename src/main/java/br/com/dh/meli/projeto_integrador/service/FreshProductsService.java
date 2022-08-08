package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.repository.IFreshProductsRepository;
import org.hibernate.engine.query.ParameterRecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FreshProductsService implements IFreshProductsService{

    @Autowired
    private IFreshProductsRepository repo;


    @Override
    public List<BatchStockDTO> getAllBatchStock() {
        if(repo.findAll().size() > 0){
            return repo.findAll();
        }
        throw new BadRequestException("BackStock is empty");
    }

    @Override
    public List<BatchStockDTO> getAllBatchStockByCategory(Optional<Category> category) {
        if (repo.getByCategory(category).size() > 0){
            return repo.getByCategory(category);
        }
        throw new BadRequestException("BatchStock is empty");
    }

    @Override
    public BatchStockDTO createBatchStock(BatchStockDTO batchStock) {
        if(batchStock.getBatchNumber() > 0){
            throw new PreconditionFailedException("BatchStock already exists");
        }
        return repo.save(batchStock);
    }


}
