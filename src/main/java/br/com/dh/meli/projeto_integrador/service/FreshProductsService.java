package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreshProductsService implements IFreshProductsService{

    @Override
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(List<Category> category) {
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
