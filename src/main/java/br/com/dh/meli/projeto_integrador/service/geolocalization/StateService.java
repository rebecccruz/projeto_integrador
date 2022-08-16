package br.com.dh.meli.projeto_integrador.service.geolocalization;

import java.util.Optional;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddStateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.CountryDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.StateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.UpdateStateDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.mapper.IStateMapper;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.model.geolocalization.StateModel;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.ICountryRepository;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * State Service
 *
 * @author Alexandre Borges Souza
 * @since 10/08/2022
 */
@Service
public class StateService implements IStateService {

    @Autowired
    private IStateRepository stateRepository;

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private CountryService countryService;
    /**
     * Add new state by country ID
     * @param stateDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean add(final AddStateDTO stateDTO) {
        Long countryID = stateDTO.getCountryID();
        CountryDTO country = this.getCountryByCountryID(countryID);
        String stateName = stateDTO.getName();
        Long countState = this.getCountStateByStateNameCountryId(stateName, countryID);
        if (countState > 0) {
            throw new PreconditionFailedException("Já existe estado registrado de acordo com país especificado.");
        }
        StateModel newState = IStateMapper.MAPPER.mappingAddStateDTOToStateModel(stateDTO);
        CountryModel countryModel = ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(country);
        countryModel.setId(countryID);
        newState.setCountry(countryModel);
        stateRepository.save(newState);
        return true;
    }

    /**
     * Update State
     * @param stateID state ID
     * @param updateStateDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean update(Long stateID, UpdateStateDTO updateStateDTO) {
        this.getStateByStateID(stateID);
        Long countryID = updateStateDTO.getCountryID();
        CountryDTO country = this.getCountryByCountryID(countryID);
        String stateName = updateStateDTO.getStateName();
        Long anotherStateID = this.getStateIDByStateNameCountryID(stateName, countryID);
        if ((anotherStateID.longValue()>0) && !anotherStateID.equals(stateID)) {
            throw new PreconditionFailedException("Já existe estado registrado de acordo com país especificado.");
        }
        StateModel stateModel = IStateMapper.MAPPER.mappingUpdateStateDTOToStateModel(updateStateDTO);
        stateModel.setId(stateID);
        CountryModel countryModel = ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(country);
        countryModel.setId(countryID);
        stateModel.setCountry(countryModel);
        stateRepository.save(stateModel);
        return true;
    }

    /**
     * Get State by state ID
     *
     * @param stateID state ID
     * @return StateDTO
     * @author Alexandre Borges Souza
     */
    @Override
    public StateDTO get(Long stateID) {
        return this.getStateByStateID(stateID);
    }

    /**
     * Delete State by state ID
     *
     * @param stateID state ID
     * @return StateDTO
     * @author Alexandre Borges Souza
     */
    @Override
    public Boolean delete(Long stateID) {
        this.getStateByStateID(stateID);
        stateRepository.deleteById(stateID);
        return true;
    }

    /**
     * Check and return country content by country ID
     * @param countryID country ID
     * @return CountryDTO
     * @author Alexandre Borges Souza
     */
    private CountryDTO getCountryByCountryID(Long countryID) {
        CountryDTO countryDTO = countryService.get(countryID);
        return countryDTO;
    }

    /**
     * Get state count by state name and country ID
     * @param name state name
     * @param countryID country id
     * @return Long
     * @author Alexandre Borges Souza
     */
    private Long getCountStateByStateNameCountryId(String name, Long countryID) {
        Long total = stateRepository.getCountStateByStateNameId(name, countryID);
        return total;
    }

    /**
     * Return state ID by state name and country ID
     *
     * @param stateName state name
     * @param countryID country ID
     * @return Long
     * @author Alexandre Borges Souza
     */
    private Long getStateIDByStateNameCountryID(String stateName, Long countryID) {
        Long stateID = stateRepository.getCountStateByStateNameId(stateName, countryID);
        return stateID;
    }

    /**
     * Return State contents by state ID
     *
     * @param stateID state ID
     * @return StateDTO
     * @author Alexandre Borges Souza
     */
    private StateDTO getStateByStateID ( Long stateID ) {
        Optional<StateModel> state = stateRepository.findById(stateID);
        if (state.isPresent()) {
            StateDTO stateDTO = IStateMapper.MAPPER.mappingStateModelToStateDTO(state.get());
            CountryDTO country = ICountryMapper.MAPPER.mappingCountryModelToCountryDTO(state.get().getCountry());
            stateDTO.setCountry(country);
            return stateDTO;
        }
        throw new NotFoundException("Estado não encontrado.");
    }
}
