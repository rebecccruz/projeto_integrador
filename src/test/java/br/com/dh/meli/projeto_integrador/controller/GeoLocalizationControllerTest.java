package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.JSONOutuputMessageDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.*;
import br.com.dh.meli.projeto_integrador.exception.ApiException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.service.ICountryService;
import br.com.dh.meli.projeto_integrador.util.geolocalization.CountryUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit test Geolocalization Controller
 *
 * @author Alexandre Borges Souza
 * @since 09/08/2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GeoLocalizationControllerTest {

    @InjectMocks
    GeoLocalizationController controller;

    @Mock
    private ICountryService service;

    /**
     * Test successfully when country payload valid and not exist in database
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void addCountry_addCountrySuccessfully_whenPayloadContentNotNull() {
        when(service.add(ArgumentMatchers.any(AddCountryDTO.class))).thenReturn(true);
        AddCountryDTO newCountry = AddCountryDTO.builder().build();
        ResponseEntity<JSONOutuputMessageDTO> result = controller.addCountry(newCountry);
        assertThat(result.getBody()).isNotNull();
        assertTrue(result.getStatusCode() == HttpStatus.CREATED);
    }

    /**
     * Test falied when country payload invalid
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void addCountry_addCountryFalied_whenPayloadContentInvalid() {
        when(service.add(ArgumentMatchers.any(AddCountryDTO.class))).thenReturn(false);
        AddCountryDTO newCountry = AddCountryDTO.builder().build();
        ApiException exception = assertThrows(ApiException.class , () -> {
            controller.addCountry(newCountry);
        });
        assertTrue(exception.getStatus().is4xxClientError());
    }

    /**
     * Test successfully update when ID country exist and content update different
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void updateCountry_updateCountrySuccessfuly_whenUpdateCountryContentValid() {
        when(service.update(ArgumentMatchers.anyLong() , ArgumentMatchers.any(UpdateCountryDTO.class))).thenReturn(true);
        Long countryID = 1l;
        UpdateCountryDTO country = UpdateCountryDTO.builder().build();
        ResponseEntity<JSONOutuputMessageDTO> result = controller.updateCountry(country, countryID);
        assertThat(result.getBody()).isNotNull();
        assertTrue(result.getStatusCode() == HttpStatus.OK);
    }

    /**
     * Test falied when upload country payload invalid
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void updateCountry_updateCountryFailed_whenUpdateCountryContentInvalid() {
        when(service.update(ArgumentMatchers.anyLong() , ArgumentMatchers.any(UpdateCountryDTO.class))).thenReturn(false);
        Long countryID = 1l;
        UpdateCountryDTO country = UpdateCountryDTO.builder().build();
        ApiException exception = assertThrows(ApiException.class , () -> {
            controller.updateCountry(country, countryID);
        });
        assertTrue(exception.getStatus().is4xxClientError());
    }

    /**
     * Test successfully get country when country ID exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void getCountry_getCountrySuccessfuly_whenCountryIDExist() {
        when(service.get(ArgumentMatchers.anyLong())).thenReturn(ICountryMapper.MAPPER.mappingCountryModelToCountryDTO(CountryUtil.getCountry()));
        Long countryID = 1l;
        ResponseEntity<CountryDTO> result = controller.getCountry(countryID);
        assertTrue(result.getStatusCode() == HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
    }

    /**
     * Test successfully delete country when country ID exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void deleteCountry_deleteCountrySuccessfully_whenCountryIDExist() {
        when(service.delete(ArgumentMatchers.anyLong())).thenReturn(true);
        Long countryID = 1l;
        ResponseEntity<JSONOutuputMessageDTO> result = controller.deleteCountry(countryID);
        assertTrue(result.getStatusCode() == HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
    }

    /**
     * Test falied delete country when internal error
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void deleteCountry_deleteCountryFalied_whenInternalError() {
        when(service.delete(ArgumentMatchers.anyLong())).thenReturn(false);
        Long countryID = 1l;
        ApiException exception = assertThrows(ApiException.class , () -> {
            controller.deleteCountry(countryID);
        });
        assertTrue(exception.getStatus().is4xxClientError());
    }
}
