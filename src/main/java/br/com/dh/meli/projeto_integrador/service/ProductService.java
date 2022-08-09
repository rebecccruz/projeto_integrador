package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IProductMapper;
import br.com.dh.meli.projeto_integrador.mapper.IShoppingCartMapper;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repo;

    @Override
    public List<Product> getAllProducts() {
        if(repo.findAll().size() > 0){
            return repo.findAll();
        }
        throw new BadRequestException("product list is empty");
    }

    @Override
    public List<Product> getAllProductsByCategory(Optional<Category> category) {
        if (repo.getProductByCategory(category).size() > 0){
            return repo.getProductByCategory(category);
        }
        throw new BadRequestException("product list is empty");
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getBatchStock().getBatchNumber() > 0){
            throw new PreconditionFailedException("product already exists");
        }
        return repo.save(product);
    }
}
