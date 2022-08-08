package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;

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
     */
    Boolean add(AddCountryDTO countryDTO);
}
