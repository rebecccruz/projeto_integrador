package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCart);
    ShoppingCart updateShoppingCart(ShoppingCartDTO shoppingCart,long shoppingCartId);
    ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart);
}
