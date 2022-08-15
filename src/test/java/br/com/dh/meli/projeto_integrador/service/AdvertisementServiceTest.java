package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.IAdvertisementMapper;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.repository.IAdvertisementRepository;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import br.com.dh.meli.projeto_integrador.util.AdvertisementUtil;
import br.com.dh.meli.projeto_integrador.util.BatchStocksTestUtil;
import br.com.dh.meli.projeto_integrador.util.SellerTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advertisement Service Test
 *
 * @author Larissa Navarro
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdvertisementServiceTest {

    @InjectMocks
    AdvertisementService service;

    @Mock
    IAdvertisementRepository repo;

    @Mock
    private IBatchStockService batchStockService;

    @Mock
    private ISellerRepository sellerRepository;

    /**
     * Successfully all Advertisements when list is not empty
     *
     * @author Larissa Navarro
     */
    @Test
    @DisplayName("Get all Advertisements when list is not empty")
    void getAllAdvertisements() {
        when(repo.findAll()).thenReturn(AdvertisementUtil.getAllAdvertisement());
        List<Advertisement> allAd = service.getAllAdvertisement();
        assertThat(allAd.size()).isPositive();
        assertThat(allAd.isEmpty()).isFalse();
        verify(repo, atLeastOnce()).findAll();
    }

    /**
     * Faliure throws Advertisements when list is empty
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Error 404 when list is empty")
    void getAllAdvertisements_whenListIsEmpty() {
        List<Advertisement> list = new ArrayList<>();
        when(repo.findAll()).thenReturn(list);
        NotFoundException exception = assertThrows(NotFoundException.class,() -> {
            service.getAllAdvertisement();
        });
        assertFalse(exception.getMessage().isEmpty());
    }

    /**
     * Successfully get all Advertisements by category
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Get all Advertisements by category")
    void getAllAdvertisementsByCategory() {
        Optional<String> category = Optional.ofNullable("FS");
        Optional<Category> categoryBy = Optional.of(Category.getEnumName(category.get()));
        List<Advertisement> list = AdvertisementUtil.getAllAdvertisement();
        when(repo.getAdvertisementByCategory(ArgumentMatchers.any())).thenReturn(list);
        List<Advertisement> allAd = service.getAllAdvertisementByCategory(categoryBy);
        assertThat(allAd.size()).isPositive();
        verify(repo, atLeastOnce()).getAdvertisementByCategory(categoryBy);
    }

    /**
     * Successfully throws when get all Advertisements by category
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Error 404 category not found when list is empty")
    void getAllAdvertisementsByCategory_whenListIsEmpy() {
        List<Advertisement> list = new ArrayList<>();
        when(repo.getAdvertisementByCategory(ArgumentMatchers.any())).thenReturn(list);
        Optional<String> category = Optional.ofNullable("FS");
        Optional<Category> categoryBy = Optional.of(Category.getEnumName(category.get()));
        NotFoundException exception = assertThrows(NotFoundException.class,() -> {
            service.getAllAdvertisementByCategory(categoryBy);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("Advertisement list is empty"));
    }

    /**
     * Successfully create advertisement
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Create Advertisement")
    void createAdvertisement_returnAdvertisementDTO_whenContentValid() {
        AdvertisementDTO dto = AdvertisementUtil.advertisementDTO();
        BatchStock batchStock = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(BatchStocksTestUtil.batchStockDTOSampleOne());
        batchStock.setDueDate(LocalDate.of(2022, 7, 1));
        when(batchStockService.findByProductId(ArgumentMatchers.anyString())).thenReturn(batchStock);
        when(sellerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(SellerTestUtil.sellerGenerator()));
        when(repo.save(ArgumentMatchers.any(Advertisement.class))).thenReturn(
                IAdvertisementMapper.MAPPER.advertisementDTOToModel(dto)
        );
        AdvertisementDTO createAd = service.createAdvertisement(dto);
        assertThat(createAd).isNotNull();
        verify(repo, times(1)).save(ArgumentMatchers.any(Advertisement.class));
    }

    /**
     * Successfully throws when create advertisement
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    void createAdvertisement_returnThrow_whenBetweenInvalidDays () {
        AdvertisementDTO dto = AdvertisementUtil.advertisementDTO();
        BatchStock batchStock = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(BatchStocksTestUtil.batchStockDTOSampleOne());
        when(batchStockService.findByProductId(ArgumentMatchers.anyString())).thenReturn(batchStock);
        when(sellerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(SellerTestUtil.sellerGenerator()));
        when(repo.save(ArgumentMatchers.any(Advertisement.class))).thenReturn(
                IAdvertisementMapper.MAPPER.advertisementDTOToModel(dto)
        );
        BadRequestException exception = assertThrows(BadRequestException.class,() -> {
            service.createAdvertisement(dto);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("Not possible create advertisement"));
    }

    /**
     * Successfully result find advertisement by ID
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    public void getAdvertisementById_returnAdvertisement_whenAdvertisementHaveID() {
        when(repo.findById(1L)).thenReturn(Optional.of(AdvertisementUtil.advertisementGenerator()));
        Advertisement result = service.getAdvertisementById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isPositive();
        verify(repo, times(1)).findById(1l);
    }

    /**
     * Successfully throws when advertisement not found by ID
     *
     * @author Larissa Navarro
     * @author Alexandre Borges Souza
     */
    @Test
    public void getAdvertisementById_returnThrow_whenAdvertisementIDNotFound () {
        when(repo.findById(1L)).thenReturn(Optional.ofNullable(null));
        NotFoundException exception = assertThrows(NotFoundException.class,() -> {
            service.getAdvertisementById(1L);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("Advertisement not found"));
    }
}
