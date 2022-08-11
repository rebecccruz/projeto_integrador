package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {
    Optional<BatchStock> findBatchStockByProductId(String productId);
}
