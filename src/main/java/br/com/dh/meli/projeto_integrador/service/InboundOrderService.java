package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.model.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class InboundOrderService implements IInboundOrderService {

    /**
     * Save entry order
     *
     * @param batchStock
     * @return null
     * @author Evelyn Cristini Oliveira e Lucas Pinheiro
     */
    public Void saveInboundOrder(BatchStock batchStock) {
        System.out.println(batchStock.toString() + " salvo com sucesso!");
        return null;
    }

    /**
     * Create entry order
     *
     * @param inboundOrder
     * @return dtoInboundOrder
     * @author Evelyn Cristini Oliveira e Lucas Pinheiro
     */

    // TODO: Considerando que produtos estejam registrados em algum warehouse
    public InboundOrderDTO createInboundOrder(InboundOrder inboundOrder) {
        inboundOrder.getBatchStock().stream().forEach(product -> {
            // TODO: DESDE que o produto de um Vendedor é registrado
            if (Seller.class.getName().isEmpty()) {
                // TODO: E que o armazém é válido
                if (Warehouse.class.getName().isEmpty()) {
                    // TODO: E que o representante pertence ao armazém
                    if (Representant.class.getName().isEmpty() == ) {
                        // TODO: E que o setor corresponde ao tipo de produto
                        if ()
                        System.out.println("Deu certo ate aqui.");
                    }
                }
            }
            saveInboundOrder(product);
        });
        InboundOrderDTO dtoInboundOrder = new InboundOrderDTO(new InboundOrder());
        return dtoInboundOrder;
    }

    //public updateInboundOrder() {}
}
