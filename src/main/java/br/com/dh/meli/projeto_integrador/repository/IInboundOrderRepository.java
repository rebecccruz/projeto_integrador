package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IInboundOrderRepository extends JpaRepository<InboundOrder,Integer> {
    Optional<InboundOrder> findInboundOrderByOrderNumber(Integer orderNumber);
}
