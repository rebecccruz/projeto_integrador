package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.CountryDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.UpdateCountryDTO;
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
     * @author Alexandre Borges Souza
     */
    CountryModel mappingCountryDTOToCountryModel(AddCountryDTO country);

    /**
     * Mapping UpdateCountryDTO to CountryModel
     * @param countryDTO country DTO Class
     * @return CountryModel
     * @author Alexandre Borges Souza
     */
    CountryModel mappingUpdateCountryDTOToCountryModel(UpdateCountryDTO countryDTO);

    /**
     * Mapping CountryModel to CountryDTO
     * @param country Country Model to mapper
     * @return CountryDTO
     * @author Alexandre Borges Souza
     */
    CountryDTO mappingCountryModelToCountryDTO(CountryModel country);

    /**
     * Mapping CountryDTO to CountryModel
     *
     * @param country
     * @return CountryModel
     */
    CountryModel mappingCountryDTOToCountryModel(CountryDTO country);
}
