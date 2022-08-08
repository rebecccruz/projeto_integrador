package br.com.dh.meli.projeto_integrador.model.geolocalization;

import lombok.Builder;
import lombok.Data;

/**
 * Geolocalization/City Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
public class CityModel {
    private Long id;
    private String name;
    private Long state_id;
}
