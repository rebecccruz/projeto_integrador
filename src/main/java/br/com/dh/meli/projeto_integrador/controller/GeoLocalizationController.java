package br.com.dh.meli.projeto_integrador.controller;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.dto.JSONOutuputMessageDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddStateDTO;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.repository.ICountryRepo;
import br.com.dh.meli.projeto_integrador.service.CountryService;
import br.com.dh.meli.projeto_integrador.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ICountryService countryService;

    /**
     * Add a new country
     *
     * @param newCountry payload for add new country
     * @return ResponseEntity<JSONOutuputMessageDTO>
     * @author Alexandre Borges Souza
     */
    @PostMapping("/country")
    public ResponseEntity<JSONOutuputMessageDTO> addCountry(@RequestBody @Valid AddCountryDTO newCountry) {
        Boolean result = countryService.add(newCountry);
        if (result) {
            JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                    .builder()
                    .title("Sucesso")
                    .message("País cadastrado com sucesso.")
                    .status(HttpStatus.CREATED)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(outuputMessageDTO);
        }
        throw new PreconditionFailedException("Houve problema interno. Por favor tente novamente mais tarde.");
    }

    /**
     * Add a new state
     *
     * @param newState payload for add new state
     * @return ResponseEntity<JSONOutuputMessageDTO>
     * @author Alexandre Borges Souza
     */
    @PostMapping("/state")
    public ResponseEntity<JSONOutuputMessageDTO> addState(@RequestBody @Valid AddStateDTO newState) {
        JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                .builder()
                .title("Sucesso")
                .message("Estado cadastrado com sucesso")
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(outuputMessageDTO);
    }
}
