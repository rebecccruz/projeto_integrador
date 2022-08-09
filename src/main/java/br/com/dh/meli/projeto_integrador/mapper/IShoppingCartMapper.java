package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.dto.geolocalization.AddCountryDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface IShoppingCartMapper {
    IShoppingCartMapper MAPPER = Mappers.getMapper(IShoppingCartMapper.class);
    ShoppingCart shoppingCartDTOtoModel(ShoppingCartDTO shoppingCart);
    ShoppingCartDTO shoppingCarttoDTO(ShoppingCart shoppingCart);
}
