package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.CustomerDTO;
import br.com.dh.meli.projeto_integrador.model.Customer;

public interface ICostumerService {
    public Customer getCostumerById(Long id);
    public CustomerDTO createCostumer(CustomerDTO dto);
}
