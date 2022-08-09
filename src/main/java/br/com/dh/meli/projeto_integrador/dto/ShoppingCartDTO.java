package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private List<Item> items;
    private Status status;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.items = shoppingCart.getItems();
        this.status = shoppingCart.getStatus();
    }
}
