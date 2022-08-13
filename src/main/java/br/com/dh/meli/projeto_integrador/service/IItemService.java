package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.model.Item;

public interface IItemService {

    Item getItemById(Long id);
    Item createItem(ItemDTO dto, Long shoppingCartId);

    ItemDTO convertToDTO(Item item);
    void save(Item item);

}
