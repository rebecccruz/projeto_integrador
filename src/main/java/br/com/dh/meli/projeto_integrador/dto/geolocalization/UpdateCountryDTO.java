package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateCountryDTO {
    @NotNull(message = "Nome do país é obrigatório")
    @JsonProperty("country_name")
    private String name;
    @NotNull(message = "Sigla é obrigatório. Deve conter mínimo e máximo 2 caracteres.")
    @Size(min = 2, max = 2)
    @JsonProperty("country_initials")
    private String initials;
}
