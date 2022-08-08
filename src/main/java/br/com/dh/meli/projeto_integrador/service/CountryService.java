package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.ICountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

/**
 * Service for Country Repository
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepo repository;

    /**
     * Add new country
     *
     * @param countryDTO payload of contents for add new country
     * @return Boolean
     */
    @Override
    public Boolean add(AddCountryDTO countryDTO) throws RuntimeException {
        try {
            CountryModel newCountry = ICountryMapper.MAPPER.addCountryDTOToCountryModel(countryDTO);
            CountryModel result = repository.save(newCountry);
            return result.getId() > 0;
        }
        catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
