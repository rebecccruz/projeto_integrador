package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.IAdvertisementMapper;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.IAdvertisementRepository;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AdvertisementService implements IAdvertisementService {

    @Autowired
    private IAdvertisementRepository repo;

    @Autowired
    private IBatchStockService batchStockService;
    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public Advertisement getAdvertisementById(Long id){
        Optional<Advertisement> advertisement = repo.findById(id);
        if(advertisement.isEmpty()){
            throw new NotFoundException("Advertisement not found");
        }
        return advertisement.get();
    }

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
    public AdvertisementDTO createAdvertisement(AdvertisementDTO dto) {
        validAdvertisement(dto);
        Advertisement advertisement = IAdvertisementMapper.MAPPER.advertisementDTOToModel(dto);
        dto = IAdvertisementMapper.MAPPER.advertisementToDTO(advertisement);
        repo.save(advertisement);
        return dto;
    }
    private void validAdvertisement(AdvertisementDTO dto){
        BatchStock batchStock = batchStockService.findByProductId(dto.getProductId());
        Seller seller = sellerRepository.findById(dto.getSeller().getId()).get();
        long differenceData =  DAYS.between(batchStockService.findByProductId(dto.getProductId()).getDueDate(),LocalDate.now());
        if(batchStock == null || seller == null || differenceData < 21){
            throw new BadRequestException("Not possible create advertisement");
        }
    }
}
