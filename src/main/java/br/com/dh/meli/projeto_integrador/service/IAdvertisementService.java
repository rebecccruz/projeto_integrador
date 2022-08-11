package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Advertisement;

import java.util.List;
import java.util.Optional;

public interface IAdvertisementService {

    List<Advertisement> getAllAdvertisement();
    List<Advertisement> getAllAdvertisementByCategory(Optional<Category> category);
    AdvertisementDTO createAdvertisement(AdvertisementDTO advertisement);

}
