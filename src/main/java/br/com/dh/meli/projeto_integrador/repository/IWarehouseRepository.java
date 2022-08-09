package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IWarehouseRepository extends JpaRepository<Warehouse,Long> {
    Optional<Warehouse> findWarehouseByCode(String code);
}
