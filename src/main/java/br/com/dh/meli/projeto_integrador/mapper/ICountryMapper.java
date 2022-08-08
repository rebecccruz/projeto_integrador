package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * Mapping Country DTO to Model / Model to DTO
 *
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
@Mapper
public interface ICountryMapper {
    ICountryMapper MAPPER = Mappers.getMapper(ICountryMapper.class);

    /**
     * Mapping AddCountryDTO to CountryModel
     *
     * @param country mapping AddCountryDTO
     * @return CountryModel
     */
    CountryModel addCountryDTOToCountryModel(AddCountryDTO country);
}
