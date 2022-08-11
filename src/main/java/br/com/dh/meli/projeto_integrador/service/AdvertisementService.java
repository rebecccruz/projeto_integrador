package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IAdvertisementMapper;
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
    public List<Advertisement> getAllAdvertisement() {
        if(repo.findAll().size() > 0){
            return repo.findAll();
        }
        throw new NotFoundException("Advertisement list is empty");
    }

    @Override
    public List<Advertisement> getAllAdvertisementByCategory(Optional<Category> category) {
        if (repo.getAdvertisementByCategory(category).size() > 0){
            return repo.getAdvertisementByCategory(category);
        }
        throw new NotFoundException("Advertisement list is empty");
    }

    @Override
    public Advertisement createAdvertisement(AdvertisementDTO advertisement) {
        try{
            Advertisement advertisementModel = IAdvertisementMapper.MAPPER.advertisementDTOToModel(advertisement);
            return repo.save(advertisementModel);
        }
        catch(Exception e){
            throw new BadRequestException("Not possible to create");
        }
    }
}
