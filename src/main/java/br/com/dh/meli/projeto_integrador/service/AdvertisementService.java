package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
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
    private IBatchStockRepository batchStockRepository;
    @Autowired
    private ISellerRepository sellerRepository;

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
    public Advertisement createAdvertisement(AdvertisementDTO dto) {
        validAdvertisement(dto);
        Advertisement advertisement = IAdvertisementMapper.MAPPER.advertisementDTOToModel(dto);
        return  repo.save(advertisement);
    }
    private void validAdvertisement(AdvertisementDTO dto){
        BatchStock batchStock = batchStockRepository.findByProdutId(dto.getProductId());
        Seller seller = sellerRepository.findById(dto.getSellerId()).get();
        long differenceData =  DAYS.between(batchStockRepository.getProductDueDate(dto.getProductId()),LocalDate.now());

        if(batchStock == null && seller == null && differenceData < 21){
            throw new BadRequestException("Not possible create advertisement");
        }
    }
}
