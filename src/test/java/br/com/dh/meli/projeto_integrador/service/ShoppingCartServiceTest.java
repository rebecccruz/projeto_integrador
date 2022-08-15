package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import br.com.dh.meli.projeto_integrador.util.ShoppingCartUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService service;

    @Mock
    IShoppingCartRepository repo;

    @BeforeEach
    public void setup() {
        BDDMockito
                .when(repo.save(ArgumentMatchers.any(ShoppingCart.class)))
                .thenReturn(ShoppingCartUtil.shoppingCartGenerator());
    }

    @Test
    @DisplayName("Create shopping cart")
    void createShoppingCart() {
        ShoppingCart newShoppingCart = ShoppingCartUtil.shoppingCartGenerator();
        ShoppingCart createdShopCart = service.createShoppingCart();

        assertThat(shoppingCartDTO).isEqualTo(createShopCart);
        assertThat(createShopCart).isNotNull();
    }

    @Test
    @DisplayName("Get shopping cart by id when shopping cart id already exists")
    void getShoppingCartById_whenIdExists() {
        long id = 1;
        ShoppingCart shoppingCart = service.getShoppingCartById(id);

    }

    @Test
    @DisplayName("Create shopping cart when shopping cart already exists")
    void createShoppingCart_whenShoppingCartAlreadyExists() {
    }
}
