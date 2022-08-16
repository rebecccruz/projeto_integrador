package br.com.dh.meli.projeto_integrador.service.geolocalization;

import java.util.Optional;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.*;
import br.com.dh.meli.projeto_integrador.exception.ApiException;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.ICountryRepository;
import br.com.dh.meli.projeto_integrador.util.geolocalization.CountryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

/**
 * Class of Country Service layer Unit Test
 *
 * @author Alexandre Borges Souza
 * @since 09/08/2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CountryServiceTest {

    @InjectMocks
    private CountryService service;

    @Mock
    private ICountryRepository repo;

    /**
     * Test with successfully add new country
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void add_saveCountry_whenValidNewCountryContent() {
        AddCountryDTO countryDTO = AddCountryDTO.builder().build();
        countryDTO.setName("Brasil");
        countryDTO.setInitials("BR");
        CountryModel countryModel = ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(countryDTO);
        when(repo.save(ArgumentMatchers.any(CountryModel.class))).thenReturn(CountryUtil.addNewCountryWithParamsByMock(countryModel));
        Boolean result = service.add(countryDTO);
        assertTrue(result.booleanValue());
        verify(repo, Mockito.times(1)).save(ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(countryDTO));
    }

    /**
     * Test fail when add new country with empty contents payload
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void add_returnFalse_whenEmptyNewCountryContent() {
        AddCountryDTO countryDTO = AddCountryDTO.builder().build();
        when(repo.save(ArgumentMatchers.any(CountryModel.class))).thenReturn(CountryUtil.addNewCountryWithoutContentsByMock());
        Boolean result = service.add(countryDTO);
        assertFalse(result.booleanValue());
        AddCountryDTO country = AddCountryDTO.builder().build();
        country.setName("Brasil");
        country.setInitials("BR");
        CountryModel countryModel = CountryUtil.addNewCountryWithParamsByMock(ICountryMapper.MAPPER.mappingCountryDTOToCountryModel(countryDTO));
        verify(repo, Mockito.never()).save(countryModel);
    }

    /**
     * Failure when add new country with invalid contents payload and return DataIntegrityViolationException throws
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void add_returnThrows_whenInvalidNewCountryContent() {
        when(repo.save(ArgumentMatchers.any(CountryModel.class))).thenThrow(new DataIntegrityViolationException("faliure"));
        AddCountryDTO countryDTO = AddCountryDTO.builder().build();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.add(countryDTO) );
        assertFalse(exception.getMessage().isBlank());
    }

    /**
     * Test successfully when update country
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void update_updateCountry_whenValidCountryContent() {
        UpdateCountryDTO countryDTO = UpdateCountryDTO.builder().build();
        countryDTO.setName("Brazil");
        countryDTO.setInitials("BR");
        Long countryID = 1L;
        CountryModel countryModel = CountryUtil.generateCountryModel();
        countryModel.setId(countryID);
        countryModel.setName(countryDTO.getName());
        countryModel.setInitials(countryDTO.getInitials());
        when(repo.findById(anyLong())).thenReturn(Optional.of(CountryUtil.getCountry()));
        when(repo.save(ArgumentMatchers.any(CountryModel.class))).thenReturn(countryModel);
        Boolean result = service.update(countryID, countryDTO);
        assertTrue(result.booleanValue());
        CountryModel updateCountry = ICountryMapper.MAPPER.mappingUpdateCountryDTOToCountryModel(countryDTO);
        updateCountry.setId(countryID);
        verify(repo, Mockito.times(1)).save(updateCountry);
    }

    /**
     * Update failure test when content is the same as registered in the database
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void update_throwException_whenCountryParamsEquals() {
        UpdateCountryDTO countryDTO = UpdateCountryDTO.builder().build();
        countryDTO.setName("Brasil");
        countryDTO.setInitials("BR");
        Long countryID = 1L;
        CountryModel countryModel = CountryUtil.generateCountryModel();
        countryModel.setId(countryID);
        countryModel.setName(countryDTO.getName());
        countryModel.setInitials(countryDTO.getInitials());
        when(repo.findById(anyLong())).thenReturn(Optional.of(CountryUtil.getCountry()));
        when(repo.save(ArgumentMatchers.any(CountryModel.class))).thenReturn(countryModel);
        ApiException exception = assertThrows(ApiException.class, () -> {
            service.update(countryID, countryDTO);
        });
        assertThat(exception.getMessage().equalsIgnoreCase("Não houve necessidade de atualizar"));
        CountryModel updateCountry = ICountryMapper.MAPPER.mappingUpdateCountryDTOToCountryModel(countryDTO);
        updateCountry.setId(countryID);
        verify(repo, Mockito.never()).save(updateCountry);
    }

    /**
     * Update failure test when country ID not exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void update_throwException_whenCountryIDNotExist() {
        UpdateCountryDTO countryDTO = UpdateCountryDTO.builder().build();
        Long countryID = 2L;
        when(repo.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        ApiException exception = assertThrows(ApiException.class,() -> {
            service.update(countryID, countryDTO);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("País não encontrado"));
    }

    /**
     * Delete successfully when country ID exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void delete_deleteCountry_whenIDCountryExist() {
        Long countryID = CountryUtil.getCountry().getId();
        when(repo.findById(anyLong())).thenReturn(Optional.of(CountryUtil.getCountry()));
        willDoNothing().given(repo).deleteById(anyLong());
        Boolean result = service.delete(countryID);
        assertTrue(result.booleanValue());
        verify(repo).deleteById(countryID);
    }

    /**
     * Delete failure when country ID not exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void delete_deleteCountryFalied_whenIDCountryNotExist() {
        Long countryID = CountryUtil.getCountry().getId();
        when(repo.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        ApiException exception = assertThrows(ApiException.class,() -> {
           service.delete(countryID);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("País não encontrado"));
    }

    /**
     * Successfully get country content when country ID exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void get_getCountry_whenCountryIDExist() {
        Long countryID = CountryUtil.getCountry().getId();
        when(repo.findById(anyLong())).thenReturn(Optional.of(CountryUtil.getCountry()));
        CountryDTO country = service.get(countryID);
        assertThat(country).isNotNull();
        assertFalse(country.getName().isEmpty());
    }

    /**
     * Failure get country content when country ID not exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void get_getCountryFalied_wheCountryIDNotExist() {
        Long countryID = 2L;
        when(repo.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        ApiException exception = assertThrows(ApiException.class,() -> {
            service.get(countryID);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("País não encontrado"));
    }
}
