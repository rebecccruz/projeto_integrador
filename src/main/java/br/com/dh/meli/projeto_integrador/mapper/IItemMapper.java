package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.model.Item;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface IItemMapper {
    IItemMapper MAPPER = Mappers.getMapper(IItemMapper.class);
    Item mappingItemDTOItem(ItemDTO dto);
    ItemDTO mappingItemToItemDTO(Item item);
    List<ItemDTO> map(List<Item> items);
    List<Item> mapDTO(List<ItemDTO> items);
}
