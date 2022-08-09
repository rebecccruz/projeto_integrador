package br.com.dh.meli.projeto_integrador.mapper;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IInboundOrderMapper {
    IInboundOrderMapper MAPPER = Mappers.getMapper(IInboundOrderMapper.class);
    InboundOrder mappingInboundOrderDTOToInboundOrder(InboundOrderDTO inboundOrderDTO);
}
