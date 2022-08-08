package br.com.dh.meli.projeto_integrador.dto.geolocalization;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for payload for add new Geolocalization/State
 *
 * @author Alexandre Borges Souza
 */
@Data
@Builder
public class AddStateDTO {
    @NotNull(message = "Título é obrigatório")
    @JsonProperty("state_name")
    private String name;
    @NotNull(message = "Sigla é obrigatório")
    @Size(min = 2, max = 2)
    @JsonProperty("state_initials")
    private String initials;
    @NotNull(message = "Timezone é obrigatório")
    @JsonProperty("state_timezone")
    private String timezone;
    @NotNull(message = "ID do país obrigatório")
    @JsonProperty("country_id")
    private Long id;
}
