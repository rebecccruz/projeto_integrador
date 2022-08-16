package br.com.dh.meli.projeto_integrador.service.geolocalization;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddStateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.StateDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.UpdateStateDTO;


public interface IStateService {
    /**
     * Add new country
     *
     * @param stateDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    Boolean add(final AddStateDTO stateDTO);

    /**
     * Add new country
     *
     * @param stateDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    Boolean update(final Long stateID , final UpdateStateDTO stateDTO);

    /**
     * Get State by state ID
     * @param stateID state ID
     * @return StateDTO
     * @author Alexandre Borges Souza
     */
    StateDTO get(final Long stateID);

    /**
     * Delete State by state ID
     * @param stateID state ID
     * @return StateDTO
     * @author Alexandre Borges Souza
     */
    Boolean delete(final Long stateID);
}
