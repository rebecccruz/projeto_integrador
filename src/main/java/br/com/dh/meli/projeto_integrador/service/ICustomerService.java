package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.CustomerDTO;
import br.com.dh.meli.projeto_integrador.model.Customer;

public interface ICustomerService {
    public Customer getCustomerById(Long id);
    public CustomerDTO createCustomer(CustomerDTO dto);
}
