package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;

public interface IInboundOrderService {
    InboundOrderDTO createInboundOrder(InboundOrderDTO inboundOrder);
    InboundOrderDTO updateInboundOrder(InboundOrderDTO inboundOrder);
}
