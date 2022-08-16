package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.ISellerMapper;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService implements ISellerService {

    @Autowired
    ISellerRepository repo;

    @Override
    public Seller createSeller(SellerDTO sellerDTO) {
        Seller seller = ISellerMapper.MAPPER.mappingSellerDTOToSellerModel(sellerDTO);
        return repo.save(seller);
    }

    @Override
    public Seller findSellerById(Long id) {
        Optional<Seller> seller = repo.findById(id);
        if (seller.isEmpty()){
            throw new PreconditionFailedException("Seller does not exist");
        }
        return seller.get();
    }

    @Override
    public Seller saveSeller(Seller seller) {
        return repo.save(seller);
    }

    @Override
    public Seller updateSeller(Seller seller){
       findSellerById(seller.getId());
       return repo.save(seller);
    }

    @Override
    public Boolean deleteSeller(Seller seller) {
        repo.delete(seller);
        return true;
    }


}
