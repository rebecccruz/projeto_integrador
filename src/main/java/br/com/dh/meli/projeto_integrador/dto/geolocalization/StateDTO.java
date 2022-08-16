package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("state_initials")
    private String stateInitials;

    @JsonProperty
    private Date created_at;

    @JsonProperty
    private Date updated_at;

    @JsonProperty
    private CountryDTO country;
}
