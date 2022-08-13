package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchStocksTestUtil {

    public static BatchStockDTO generatorDTO() {
        BatchStockDTO dto = BatchStockDTO.builder().build();
        return dto;
    }

    /**
     * Method that helps to create a static batchStock object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @return returns a static batch stock
     */
    public static BatchStockDTO batchStockDTOSampleOne () {
        LocalDate now = LocalDate.of(2022, 05, 1);
        LocalDate dueDate = LocalDate.of(2025, 12, 1);
        BatchStockDTO dto = generatorDTO();
        dto.setBatchNumber(10);
        dto.setProductId("MBLSP-1234");
        dto.setInitialQuantity(7);
        dto.setCurrentQuantity(7);
        dto.setMinimumTemperature(0F);
        dto.setCurrentTemperature(30F);
        dto.setManufacturingDate(now);
        dto.getManufacturingTime();
        dto.setDueDate(dueDate);
        return dto;
    }

    public static List<BatchStockDTO> payloadForInboundOrderPayload() {
        List<BatchStockDTO> list = new ArrayList<>();
        list.add(batchStockDTOSampleOne());
        return list;
    }

    public static List<BatchStock> listOfBatchStock() {
        List<BatchStockDTO> batchStockList = payloadForInboundOrderPayload();
        return IBatchStockMapper.MAPPER.mapDTO(batchStockList);
    }
}
