package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

public interface IItemService {

    Item getItemById(Long id);
    Item createItem(ItemDTO dto, ShoppingCart shoppingCart);

    ItemDTO convertToDTO(Item item);
    void save(Item item);

}
