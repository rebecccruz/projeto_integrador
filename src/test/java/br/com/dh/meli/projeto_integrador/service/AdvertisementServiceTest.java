package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.mapper.IAdvertisementMapper;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.repository.IAdvertisementRepository;
import br.com.dh.meli.projeto_integrador.util.AdvertisementUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdvertisementServiceTest {

    @InjectMocks
    AdvertisementService service;

    @Mock
    IAdvertisementRepository repo;

    @BeforeEach
    public void setup() {
        BDDMockito
                .when(repo.findAll())
                .thenReturn(AdvertisementUtil.getAllAdvertisement());

        BDDMockito
                .when(repo.save(ArgumentMatchers.any(Advertisement.class)))
                .thenReturn(AdvertisementUtil.advertisementGenerator());

//        BDDMockito
//                .when(repo.getAdvertisementByCategory(ArgumentMatchers.any(Optional.class)))
//                .thenReturn(AdvertisementUtil.findByCategory());

    }


    @Test
    @DisplayName("Get all Advertisements when list is not empty")
    void getAllAdvertisements() {
        List<Advertisement> allAd = service.getAllAdvertisement();
        verify(repo, atLeastOnce()).findAll();

        assertThat(allAd.size()).isPositive();
        assertThat(allAd.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Error 404 when list is empty")
    void getAllAdvertisements_whenListIsEmpty() {
    }

    @Test
    @DisplayName("Get all Advertisements by category")
    void getAllAdvertisementsByCategory() {
    }

    @Test
    @DisplayName("Error 404 category not found when list is empty")
    void getAllAdvertisementsByCategory_whenListIsEmpy() {
    }

    @Test
    @DisplayName("Create Advertisement")
    void createAdvertisement() {
        AdvertisementDTO dto = AdvertisementUtil.advertisementDTO();
        Advertisement createAd = service.createAdvertisement(dto);



        //Advertisement createdAd = service.createAdvertisement(newAd);

        //assertThat(createdAd.getId()).isEqualTo(newAd.getId());
        //assertThat(createdAd).isNotNull();
    }

    @Test
    @DisplayName("Create Advertisement")
    void createAdvertisement_whenAdvertisementAlreadyExist() {
    }
}