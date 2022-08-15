package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.mapper.IInboundOrderMapper;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * InboundOrder Test Utility
 * @author Lucas Pinheiro Rocha
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
public class InboundOrderTestUtil {

    /**
     * Generate InboundOrderDTO Empty
     *
     * @return InboundOrderDTO
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static InboundOrderDTO generatorDTO () {
        InboundOrderDTO dto = InboundOrderDTO.builder().build();
        return dto;
    }

    /**
     * Generate InboundOrderDTO with Param
     *
     * @return InboundOrderDTO
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static InboundOrderDTO getEmptyOrderDTOToPayloadOne() {
        InboundOrderDTO payload = generatorDTO();
        payload.setOrderNumber(1);
        return payload;
    }

    /**
     * Generate InboundOrderDTO with Param
     *
     * @return InboundOrderDTO
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static InboundOrderDTO inboundOrderDTOPayloadOne() {
        LocalDate orderDate = LocalDate.of(2022, 05, 1);
        InboundOrderDTO payload = getEmptyOrderDTOToPayloadOne();
        payload.setSectionCode(SessionTestUtil.returnEmptySectionSampleOne().getCode());
        payload.setBatchStock(BatchStocksTestUtil.payloadForInboundOrderPayload());
        payload.setOrderDate(orderDate);
        payload.setWarehouseCode(WarehouseTestUtil.emptyWarehouseGenerator().getCode());
        return payload;
    }

    /**
     * Return InboundOrder by InboundOrderDTO param
     *
     * @param dto InboundOrderDTO
     * @return InboundOrder
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static InboundOrder inboundOrderByPayloadParam(InboundOrderDTO dto) {
        InboundOrder model = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(dto);
        model.setSection(SessionTestUtil.sectionSampleOne());
        model.setWarehouse(SessionTestUtil.sectionSampleOne().getWarehouse());
        model.setRepresentant(RepresentantTestUtil.representantSampleOne());
        return model;
    }

    /**
     * Return InboundOrder
     *
     * @return InboundOrder
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static InboundOrder inboundOrderSampleOne() {
        InboundOrder model = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(inboundOrderDTOPayloadOne());
        model.setSection(SessionTestUtil.sectionSampleOne());
        model.setWarehouse(SessionTestUtil.sectionSampleOne().getWarehouse());
        model.setRepresentant(RepresentantTestUtil.representantSampleOne());
        return model;
    }

    /**
     * Return list of InboundOrder
     *
     * @return InboundOrder
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static List<InboundOrder> inboundOrderList() {
        List<InboundOrder> inboundOrderList = new ArrayList<>();
        inboundOrderList.add(inboundOrderSampleOne());
        return inboundOrderList;
    }
}
