package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for payload for add new Geolocalization/Zip Code
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@NoArgsConstructor
public class AddZipCodeDTO {
    @NotNull(message = "Latitude é obrigatório")
    @JsonProperty("address_latitude")
    private Double latitude;
    @NotNull(message = "Longitude é obrigatório")
    @JsonProperty("address_longitude")
    private Double longitude;
    @NotNull(message = "CEP do endereço é obrigatório")
    @Size(min = 8, max = 8)
    @JsonProperty("address_zipcode")
    private String zip_code;
    @NotNull(message = "Id da cidade é obrigatório")
    @JsonProperty("city_id")
    private Long city_id;
}
