package br.com.dh.meli.projeto_integrador.model.geolocalization;

import lombok.Builder;
import lombok.Data;

/**
 * Geolocalization/State Entitie
 *
 * @author Alexandre Borges Souza
 */
@Data
@Builder
public class StateModel {
    private Long id;
    private String name;
    private String timezone;
    private Long country_id;
}
