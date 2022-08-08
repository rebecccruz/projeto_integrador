package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for payload for add new Geolocalization/Address
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "Logradouro é obrigatório")
    @JsonProperty("address_street")
    private String street;

    @NotNull(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 6)
    @JsonProperty("address_street")
    private Integer number;
}
