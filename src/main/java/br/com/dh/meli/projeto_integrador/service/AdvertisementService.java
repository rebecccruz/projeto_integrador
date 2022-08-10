package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.repository.IAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService implements IAdvertisementService {

    @Autowired
    private IAdvertisementRepository repo;

    @Override
    public List<Advertisement> getAllAdvertisements() {
        if(repo.findAll().size() > 0){
            return repo.findAll();
        }
        throw new BadRequestException("product list is empty");
    }

    @Override
    public List<Advertisement> getAllAdvertisementsByCategory(Optional<Category> category) {
        if (repo.getAdvertisementByCategory(category).size() > 0){
            return repo.getAdvertisementByCategory(category);
        }
        throw new BadRequestException("advertisement list is empty");
    }

    @Override
    public Advertisement createAdvertisement(Advertisement advertisement) {
        if(advertisement.getBatchStock().getBatchNumber() > 0){
            throw new PreconditionFailedException("advertisement already exists");
        }
        return repo.save(advertisement);
    }
}
