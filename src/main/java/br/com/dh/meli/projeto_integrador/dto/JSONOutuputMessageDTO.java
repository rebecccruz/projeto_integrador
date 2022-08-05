package br.com.dh.meli.projeto_integrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * JSON output message
 *
 * @author Alexandre Borges Souza
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONOutuputMessageDTO {

    @JsonProperty
    private String title;

    @JsonProperty
    private String message;

    @JsonProperty
    private HttpStatus status;
}
