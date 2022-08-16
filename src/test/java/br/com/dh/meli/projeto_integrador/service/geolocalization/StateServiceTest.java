package br.com.dh.meli.projeto_integrador.service.geolocalization;

import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddStateDTO;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.ICountryRepository;
import br.com.dh.meli.projeto_integrador.util.geolocalization.CountryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StateServiceTest {

    @InjectMocks
    private StateService service;

    @Mock
    private ISectionRepository stateRepository;

    @Mock
    private ICountryRepository countryRepository;

    @Test
    void add_addState_whenStateStateNotExistByCountryID() {

        AddStateDTO state = AddStateDTO.builder()
                .name("Minas Gerais")
                .initials("MG")
                .timezone("America/Sao_Paulo")
                .countryID(1L)
                .build();
        CountryModel country = CountryUtil.getCountry();
    }
}
