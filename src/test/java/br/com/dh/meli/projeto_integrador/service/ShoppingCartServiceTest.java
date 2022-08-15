package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import br.com.dh.meli.projeto_integrador.util.AdvertisementUtil;
import br.com.dh.meli.projeto_integrador.util.ItemUtil;
import br.com.dh.meli.projeto_integrador.util.ShoppingCartUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService service;

    @Mock
    ICustomerService customerService;

    @Mock
    IShoppingCartRepository repo;

    @Mock
    IItemService itemService;

    /**
     * Method that helps to create a setup before each test
     * @author Evelyn Oliveira
     * @return return void
     */
    @BeforeEach
    public void setup() {
        when(repo.save(ArgumentMatchers.any(ShoppingCart.class)))
                .thenReturn(ShoppingCartUtil.shoppingCartGenerator());
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(ShoppingCartUtil.shoppingCartGenerator()));
    }

    /**
     * Method that helps to create shopping cart when new shopping cart is valid
     * @author Evelyn Oliveira
     * @return return void
     */
    @Test
    @DisplayName("Create shopping cart when new shopping cart is valid")
    void createShoppingCart_whenValidNewShoppingCart() {
        Item item = ItemUtil.emptyItem();
        item.setShoppingCart(ShoppingCartUtil.shoppingCartGenerator());
        item.setAdvertisement(AdvertisementUtil.advertisementGenerator());

        BDDMockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong()))
                .thenReturn(ShoppingCartUtil.customerGenerator());
        BDDMockito.when(itemService.createItem(ArgumentMatchers.any(ItemDTO.class), ArgumentMatchers.anyLong()))
                .thenReturn(item);

        ShoppingCartDTO shoppingCartDTO = ShoppingCartUtil.shoppingCartDTOGenerator();
        ShoppingCart createdShopCart = service.createShoppingCart(shoppingCartDTO);

        assertThat(createdShopCart.getStatus()).isEqualTo(shoppingCartDTO.getStatus());
        assertThat(createdShopCart.getId()).isPositive();
        assertThat(createdShopCart).isNotNull();
    }

    /**
     * Method that helps to get shopping cart by id when shopping cart id already exists
     * @author Evelyn Oliveira
     * @return return void
     */
    @Test
    @DisplayName("Get shopping cart by id when shopping cart id already exists")
    void getShoppingCartById_whenIdExists() {
        long id = 1;
        ShoppingCart shoppingCart = service.getShoppingCartById(id);
        assertThat(shoppingCart.getId()).isEqualTo(ShoppingCartUtil.shoppingCartGenerator().getId());

    }

    /**
     * Method that helps to get shopping cart by id when shopping cart doesnt exists
     * @author Evelyn Oliveira
     * @return return void
     */
    @Test
    @DisplayName("Get shopping cart by id when shopping cart doesnt exists")
    void getShoppingCartById_whenIdDoesNotExists() {
        long id = 20;
        ShoppingCart shoppingCart = service.getShoppingCartById(id);

        when(repo.findById(anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.getShoppingCartById(id));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(shoppingCart.getId()).isEqualTo(ShoppingCartUtil.shoppingCartGenerator().getId());
    }

    /**
     * Method that helps to update shopping cart when shopping cart is valid
     * @author Evelyn Oliveira
     * @return return void
     */
    @Test
    @DisplayName("Update shopping cart when shopping cart is valid")
    void updateShoppingCart_whenValidShoppingCart() {
        ShoppingCart shoppingCart = ShoppingCartUtil.shoppingCartGenerator();

        when(repo.save(shoppingCart))
                .thenReturn(shoppingCart);
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(shoppingCart));

        shoppingCart.setId(1L);
        shoppingCart.setStatus(Status.ABERTO);

        ShoppingCart shoppingCartUpdated = service.updateShoppingCart(1L, Status.ABERTO);

        assertThat(shoppingCartUpdated).isNotNull();
        assertThat(shoppingCartUpdated.getStatus()).isEqualTo(shoppingCartUpdated.getStatus());
    }
}
