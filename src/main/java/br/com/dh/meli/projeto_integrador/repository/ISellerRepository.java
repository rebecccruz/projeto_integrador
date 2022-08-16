package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISellerRepository extends JpaRepository<Seller,Long> {

    Optional<Seller> findBySeller(Long id);

}
