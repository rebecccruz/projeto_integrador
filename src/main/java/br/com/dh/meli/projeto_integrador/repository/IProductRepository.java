package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p, b,s FROM Product p, BatchStock b, Section s WHERE s.category=?1")
    List<Product> getProductByCategory(Optional<Category> category);
}
