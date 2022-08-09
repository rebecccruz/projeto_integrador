package br.com.dh.meli.projeto_integrador.controller;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.*;
import br.com.dh.meli.projeto_integrador.dto.JSONOutuputMessageDTO;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.service.geolocalization.ICountryService;
import br.com.dh.meli.projeto_integrador.service.geolocalization.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Administrarion for GeoLocalization (City, State and Country)
 *
 * @author Alexandre Borges Souza
 * @since 05/08/2022
 */
@RestController
@RequestMapping("/api/v1/geo")
public class GeoLocalizationController {

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IStateService stateService;

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
     * Update Country
     *
     * @param country payload of country content
     * @param countryID country ID
     * @return ResponseEntity<JSONOutuputMessageDTO>
     */
    @PutMapping("/country/{countryID}")
    public ResponseEntity<JSONOutuputMessageDTO> updateCountry(@RequestBody @Valid UpdateCountryDTO country, @PathVariable @Valid Long countryID) {
        Boolean result = countryService.update(countryID, country);
        if (result) {
            JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                    .builder()
                    .title("Sucesso")
                    .message("País atualizado com sucesso.")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(outuputMessageDTO);
        }
        throw new PreconditionFailedException("Houve problema interno. Por favor tente novamente mais tarde.");
    }

    /**
     * Get country by country ID
     *
     * @param countryID country id
     * @return ResponseEntity<CountryDTO>
     * @author Alexandre Borges Souza
     */
    @GetMapping("/country/{countryID}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Long countryID) {
        CountryDTO result = countryService.get(countryID);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Delete country
     *
     * @param countryID country ID
     * @return ResponseEntity<JSONOutuputMessageDTO>
     */
    @DeleteMapping("/country/{countryID}")
    public ResponseEntity<JSONOutuputMessageDTO> deleteCountry(@PathVariable @Valid Long countryID) {
        Boolean result = countryService.delete(countryID);
        if (result) {
            JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                    .builder()
                    .title("Sucesso")
                    .message("País deletado com sucesso.")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(outuputMessageDTO);
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
        Boolean result = stateService.add(newState);
        if (result) {
            JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                    .builder()
                    .title("Sucesso")
                    .message("Estado cadastrado com sucesso")
                    .status(HttpStatus.CREATED)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(outuputMessageDTO);
        }
        throw new PreconditionFailedException("Houve problema interno. Por favor tente novamente mais tarde.");
    }

    /**
     * Get state by state ID
     * @param stateID state ID
     * @return ResponseEntity<StateDTO>
     * @author Alexandre Borges Souza
     */
    @GetMapping("/state/{stateID}")
    public ResponseEntity<StateDTO> getState(@PathVariable Long stateID) {
        StateDTO result = stateService.get(stateID);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/state/{stateID}")
    public ResponseEntity<JSONOutuputMessageDTO> updateState(@PathVariable Long stateID, @Valid @RequestBody UpdateStateDTO updateState) {
        Boolean result = stateService.update(stateID, updateState);
        if (result) {
            JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                    .builder()
                    .title("Sucesso")
                    .message("Estado atualizado com sucesso")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(outuputMessageDTO);
        }
        throw new PreconditionFailedException("Houve problema interno. Por favor tente novamente mais tarde.");
    }

    @DeleteMapping("/state/{stateID}")
    public ResponseEntity<JSONOutuputMessageDTO> deleteState(@PathVariable Long stateID) {
        stateService.delete(stateID);
        JSONOutuputMessageDTO outuputMessageDTO = JSONOutuputMessageDTO
                .builder()
                .title("Sucesso")
                .message("Estado atualizado com sucesso")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(outuputMessageDTO);
    }
}
