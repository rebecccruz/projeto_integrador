package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.repository.IFreshProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreshProductsService implements IFreshProductsService{

    @Autowired
    private IFreshProductsRepository repo;

    @Override
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(Optional<Category> category) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByOrder(String idOrder) {
        return null;
    }

    @Override
    public ProductDTO createProduct(Product product) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Product product) {
        return null;
    }
}
