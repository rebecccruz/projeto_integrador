package br.com.dh.meli.projeto_integrador.service.geolocalization;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.CountryDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.UpdateCountryDTO;

/**
 * Country Service
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
public interface ICountryService {

    /**
     * Add new country
     *
     * @param countryDTO payload of contents for add new country
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    Boolean add(final AddCountryDTO countryDTO);

    /**
     * Update a country by country ID
     *
     * @param countryID country ID
     * @param country payload of country content
     * @return Boolean
     * @author Alexandre Borges Souza
     */
    Boolean update(final Long countryID, final UpdateCountryDTO country);

    /**
     * Delete country by country ID
     *
     * @param countryID country ID
     * @return
     * @author Alexandre Borges Souza
     */
    Boolean delete(final Long countryID);

    /**
     * Get Country by Country ID
     *
     * @param countryID country ID
     * @return CountryDTO
     * @author Alexandre Borges Souza
     */
    CountryDTO get(final Long countryID);
}
