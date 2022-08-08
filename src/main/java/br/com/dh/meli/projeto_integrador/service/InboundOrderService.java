package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import org.springframework.stereotype.Service;

@Service
public class InboundOrderService implements IInboundOrderService {
    @Override
    public Void saveInboundOrder(BatchStock batchStock) {
        return null;
    }

    @Override
    public InboundOrderDTO createInboundOrder(InboundOrder inboundOrder) {
        return null;
    }
}
