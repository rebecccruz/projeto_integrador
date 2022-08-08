package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;

public interface IInboundOrderService {

    Void saveInboundOrder(BatchStock batchStock);
    InboundOrderDTO createInboundOrder(InboundOrder inboundOrder);

}
