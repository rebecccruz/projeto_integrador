package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for payload for add new Geolocalization/Country
 *
 * @author Alexandre Borges Souza
 */
@Data
@NoArgsConstructor
public class AddCountryDTO {
    @NotNull(message = "Título é obrigatório")
    @JsonProperty("country_name")
    private String name;
    @NotNull(message = "Sigla é obrigatório")
    @Size(min = 2, max = 2)
    @JsonProperty("country_initials")
    private String initials;
}
