package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * DTO for payload for add new Geolocalization/City
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@NoArgsConstructor
public class AddCityDTO {
    @NotNull(message = "Nome da cidade é obrigatório")
    @JsonProperty("city_name")
    private String name;
    @NotNull(message = "Id do estado é obrigatório")
    @JsonProperty("state_id")
    private Long state_id;
}
