package br.com.dh.meli.projeto_integrador.controller;
import br.com.dh.meli.projeto_integrador.dto.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.dto.JSONOutuputMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * Administrarion for GeoLocalization (City, State and Country)
 *
 * @author Alexandre Borges Souza
 */
@RestController
@RequestMapping("/api/v1/geo")
public class GeoLocalizationController {

    /**
     * Add a new country
     *
     * @param AddCountryDTO newCountry
     * @return ResponseEntity<JSONOutuputMessageDTO>
     * @author Alexandre Borges Souza
     */
    @PostMapping("/country")
    public ResponseEntity<JSONOutuputMessageDTO> addCountry(@RequestBody @Valid AddCountryDTO newCountry) {
        JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                .builder()
                .title("Sucesso")
                .message("País cadastrado com sucesso")
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(outuputMessageDTO);
    }
}
