package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.CustomerDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Customer;
import br.com.dh.meli.projeto_integrador.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CostumerService  implements ICostumerService{
    @Autowired
    private ICustomerRepository repo;

    @Override
    public Customer getCostumerById(Long id) {
        Optional<Customer> costumer = repo.findById(id);
        if(costumer.isEmpty()){
            throw new NotFoundException("Costumer not found");

        }
        return costumer.get();
    }

    @Override
    public CustomerDTO createCostumer(CustomerDTO dto) {
        return null;
    }
}
