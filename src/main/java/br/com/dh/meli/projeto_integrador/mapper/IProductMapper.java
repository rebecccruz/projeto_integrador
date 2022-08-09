package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProductMapper {
    IProductMapper MAPPER = Mappers.getMapper(IProductMapper.class);
    Product productDTOtoModel(ProductDTO product);
    ProductDTO producttoDTO(Product product);
}
