package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.repository.IItemRepository;
import br.com.dh.meli.projeto_integrador.util.AdvertisementUtil;
import br.com.dh.meli.projeto_integrador.util.BatchStocksTestUtil;
import br.com.dh.meli.projeto_integrador.util.ItemUtil;
import br.com.dh.meli.projeto_integrador.util.ShoppingCartUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ItemServiceTest {

    @InjectMocks
    private ItemService service;

    @Mock
    private IItemRepository repo;

    @Mock
    private IAdvertisementService advertisementService;

    @Mock
    private IShoppingCartService shoppingCartService;

    @BeforeEach
    public void setup() {
        BDDMockito.when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ItemUtil.emptyItem()));

        BDDMockito.when(repo.save(ArgumentMatchers.any(Item.class)))
                .thenReturn(ItemUtil.emptyItem());

        BDDMockito.when(advertisementService.getAdvertisementById(ArgumentMatchers.anyLong()))
                .thenReturn(AdvertisementUtil.advertisementGenerator());

        BDDMockito.when(shoppingCartService.getShoppingCartById(ArgumentMatchers.anyLong()))
                .thenReturn(ShoppingCartUtil.shoppingCartGenerator());
    }

    @Test
    void getItemById_When_Found() {
        Item item = service.getItemById(1L);

        assertThat(item.getId()).isEqualTo(1L);
    }

    @Test
    void getItemById_When_NotFound() {
        BDDMockito.when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.getItemById(1L));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void save() {
        service.save(ItemUtil.emptyItem());
    }

    @Test
    void createItem() {
        Item item = service.createItem(ItemUtil.emptyItemDTO(), ShoppingCartUtil.shoppingCartGenerator());

        assertThat(item.getId()).isEqualTo(ItemUtil.emptyItem().getId());
    }

    @Test
    void convertToDTO_When_BatchStock_IsNotNull() {
        Item item = ItemUtil.completeItem();

        ItemDTO dto = service.convertToDTO(item);

        assertThat(dto.getQuantity()).isEqualTo(ItemUtil.emptyItem().getQuantity());
        assertThat(dto.getBatchNumber()).isEqualTo(item.getBatchStock().getBatchNumber());
    }

    @Test
    void convertToDTO_When_BatchStock_IsNull() {
        Item item = ItemUtil.completeItem();
        item.setBatchStock(null);

        ItemDTO dto = service.convertToDTO(item);

        assertThat(dto.getQuantity()).isEqualTo(ItemUtil.emptyItem().getQuantity());
        assertThat(dto.getBatchNumber()).isNull();
    }
}
