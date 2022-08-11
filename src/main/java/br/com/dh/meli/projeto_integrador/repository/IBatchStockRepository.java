package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {
    @Query("SELECT b FROM BatchStock b WHERE b.productId=?1")
    BatchStock findByProdutId(String productId);
    @Query("SELECT b.dueDate FROM BatchStock b WHERE b.productId =?1")
    LocalDate getProductDueDate(String productId);

}
