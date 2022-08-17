package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.CustomerDTO;
import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.model.Customer;
import br.com.dh.meli.projeto_integrador.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISellerMapper {
    ISellerMapper MAPPER = Mappers.getMapper(ISellerMapper.class);
    Seller mappingSellerDTOToSellerModel(SellerDTO dto);
    SellerDTO mappingSellerModelToSellerDTO(Seller seller);
}
