package br.com.dh.meli.projeto_integrador.dto.geolocalization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CountryDTO {
    @JsonProperty("country_name")
    private String name;
    @JsonProperty("country_initials")
    private String initials;
    @JsonProperty("country_created_at")
    private Date created_at;
    @JsonProperty("country_updated_at")
    private Date updated_at;
}
