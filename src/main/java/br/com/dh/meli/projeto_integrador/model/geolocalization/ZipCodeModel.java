package br.com.dh.meli.projeto_integrador.model.geolocalization;

import lombok.Builder;
import lombok.Data;

/**
 * Geolocalization/Zip Code Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
public class ZipCodeModel {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String zip_code;
    private Long city_id;
}
