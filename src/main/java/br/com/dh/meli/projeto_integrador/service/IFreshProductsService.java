package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;

import java.util.List;
import java.util.Optional;

public interface IFreshProductsService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllProductsByCategory(Optional<Category> category);
    List<ProductDTO> getAllProductsByOrder(String idOrder);
    ProductDTO createProduct(Product product);
    ProductDTO updateProduct(Product product);
}
