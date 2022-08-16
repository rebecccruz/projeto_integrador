package br.com.dh.meli.projeto_integrador.service.geolocalization;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.*;
import br.com.dh.meli.projeto_integrador.exception.*;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service for Country Repository
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository repository;

    /**
     * Add new country
     *
     * @param countryDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean add(final AddCountryDTO countryDTO) throws DataIntegrityViolationException {
        try {
            CountryModel newCountry = ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(countryDTO);
            CountryModel result = repository.save(newCountry);
            return result.getId() > 0;
        }
        catch (DataIntegrityViolationException e) {
            String message = String.format("Erro: %s\nCausa: %s", e.getMessage(), e.getCause());
            throw new BadRequestException(message);
        }
    }

    /**
     * Update country
     *
     * @param countryID country ID
     * @param country payload of update country content
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean update(final Long countryID, final UpdateCountryDTO country) throws DataIntegrityViolationException {
        CountryModel getCountry = this.checkCountryExistByCountryID(countryID);

        if ((!getCountry.getName().equalsIgnoreCase(country.getName())) ||
                (!getCountry.getInitials().equalsIgnoreCase(country.getInitials()))) {
            CountryModel updateCountry = ICountryMapper.MAPPER.mappingUpdateCountryDTOToCountryModel(country);
            updateCountry.setId(countryID);
            repository.save(updateCountry);
            return true;
        }
        throw new PreconditionFailedException("Não houve necessidade de atualizar");
    }

    /**
     * Delete country by country ID
     *
     * @param countryID country ID
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean delete(final Long countryID) throws DataIntegrityViolationException {
        this.checkCountryExistByCountryID(countryID);
        repository.deleteById(countryID);
        return true;
    }

    /**
     * Get country
     *
     * @param countryID country ID
     * @return CountryDTO
     */
    @Override
    public CountryDTO get(Long countryID) {
        CountryModel findCountry = this.checkCountryExistByCountryID(countryID);
        CountryDTO countryDTO = ICountryMapper.MAPPER.mappingCountryModelToCountryDTO(findCountry);
        return countryDTO;
    }

    /**
     *
     * Check country exist and return country content
     *
     * @param countryID country ID
     * @return CountryModel
     * @author Alexandre Borges Souza
     */
    private CountryModel checkCountryExistByCountryID(Long countryID) {
        Optional<CountryModel> findCountry = repository.findById(countryID);
        if (!findCountry.isPresent()) {
            throw new NotFoundException("País não encontrado");
        }
        return findCountry.get();
    }
}
