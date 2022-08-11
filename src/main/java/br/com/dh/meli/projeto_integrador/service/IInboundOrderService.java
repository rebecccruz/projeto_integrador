package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;

public interface IInboundOrderService {
    InboundOrder createInboundOrder(InboundOrderDTO inboundOrder);
    InboundOrder updateInboundOrder(InboundOrderDTO inboundOrder);
    InboundOrderDTO convertToDto(InboundOrder inboundOrder);
}
