package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.mapper.IInboundOrderMapper;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InboundOrderTestUtil {

    public static InboundOrderDTO generatorDTO () {
        InboundOrderDTO dto = InboundOrderDTO.builder().build();
        return dto;
    }

    public static InboundOrderDTO getEmptyOrderDTOToPayloadOne() {
        InboundOrderDTO payload = generatorDTO();
        payload.setOrderNumber(1);
        return payload;
    }

    public static InboundOrderDTO inboundOrderDTOPayloadOne() {
        LocalDate orderDate = LocalDate.of(2022, 05, 1);
        InboundOrderDTO payload = getEmptyOrderDTOToPayloadOne();
        payload.setSectionCode(SessionTestUtil.returnEmptySectionSampleOne().getCode());
        payload.setBatchStocks(BatchStocksTestUtil.payloadForInboundOrderPayload());
        payload.setOrderDate(orderDate);
        payload.setWarehouseCode(WarehouseTestUtil.emptyWarehouseGenerator().getCode());
        return payload;
    }

    public static InboundOrder inboundOrderSampleOne() {
        InboundOrder model = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(inboundOrderDTOPayloadOne());
        model.setSection(SessionTestUtil.sectionSampleOne());
        model.setWarehouse(SessionTestUtil.sectionSampleOne().getWarehouse());
        model.setRepresentant(RepresentantTestUtil.representantSampleOne());
        return model;
    }

    public static List<InboundOrder> inboundOrderList() {
        List<InboundOrder> inboundOrderList = new ArrayList<>();
        inboundOrderList.add(inboundOrderSampleOne());
        return inboundOrderList;
    }
}
