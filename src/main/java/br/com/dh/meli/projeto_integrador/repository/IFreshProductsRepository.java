package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFreshProductsRepository extends JpaRepository<Integer, BatchStock> {
}
