package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    ISellerRepository repo;

    public Optional<Seller> saveSeller(Seller seller) {
        if (repo.findBySeller(seller.getId()).isPresent())
            return Optional.empty();
        return Optional.of(repo.save(seller));
    }

    public Optional<Seller> updateSeller(Seller seller){
        if(repo.findBySeller(seller.getId()).isPresent()){
            Optional<Seller> findSeller = repo.findBySeller(seller.getId());

            if((findSeller.isPresent() && findSeller.get().getId() != seller.getId()))
                throw new BadRequestException("Seller already registered");
            return Optional.ofNullable(repo.save(seller));
        }
        return Optional.empty();
    }


}
