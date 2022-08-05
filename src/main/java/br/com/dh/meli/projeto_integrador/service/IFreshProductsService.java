package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;

import java.util.List;

public interface IFreshProductsService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllProductsByCategory(List<Category> category);
    List<ProductDTO> getAllProductsByOrder(String idOrder);
    ProductDTO createProduct(Product product);
    ProductDTO updateProduct(Product product);


}
