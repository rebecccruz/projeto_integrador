package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

/**
 * Class for update State payload
 * @author Alexandre Borges Souza
 * @since 10/08/2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStateDTO {

    @NotNull(message = "Título do estado é obrigatório")
    @JsonProperty("state_name")
    private String stateName;

    @NotNull(message = "Sigla é obrigatório")
    @JsonProperty("state_initials")
    private String stateInitials;

    @NotNull(message = "Timezone é obrigatório")
    @JsonProperty("state_timezone")
    private String stateTimezone;

    @NotNull(message = "ID do país é obrigatório")
    @JsonProperty("country_id")
    private Long countryID;
}
