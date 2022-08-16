package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.model.Advertisement;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAdvertisementMapper {
    IAdvertisementMapper MAPPER = Mappers.getMapper(IAdvertisementMapper.class);
    Advertisement advertisementDTOToModel(AdvertisementDTO advertisement);
    AdvertisementDTO advertisementToDTO(Advertisement advertisement);
}
