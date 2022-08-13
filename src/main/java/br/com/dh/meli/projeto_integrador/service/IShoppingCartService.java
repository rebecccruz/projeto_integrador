package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart getShoppingCartById(Long id);
    ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCart);
    ShoppingCart updateShoppingCart(Long shoppingCartId, Status status);
    ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart);
}
