package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCart);
    ShoppingCartDTO updateShoppingCart(ShoppingCart shoppingCart);
}
