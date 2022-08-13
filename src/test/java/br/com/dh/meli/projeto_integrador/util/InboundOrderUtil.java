package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InboundOrderUtil {

    /**
     * Method that helps to create a customized batchStock object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @param batchNumber batch number given by seller
     * @param productId productId given by seller
     * @param currentTemperature current temperature of the product
     * @param minimumTemperature minimum temperature of the product
     * @param initialQuantity fully quantity of the product
     * @param currentQuantity quantity of the product that should be inserted on database
     * @param manufacturingDate product manufacturing date
     * @param manufacturingTime product manufacturing date time
     * @return returns a customized batch stock
     */
    private BatchStockDTO batchStockGenerator(
            int batchNumber,
            String productId,
            Float currentTemperature,
            Float minimumTemperature,
            int initialQuantity,
            int currentQuantity,
            LocalDate manufacturingDate,
            LocalDateTime manufacturingTime,
            LocalDate dueDate
    ) {
        return BatchStockDTO.builder()
                .batchNumber(batchNumber)
                .productId(productId)
                .currentTemperature(currentTemperature)
                .minimumTemperature(minimumTemperature)
                .initialQuantity(initialQuantity)
                .currentQuantity(currentQuantity)
                .manufacturingDate(manufacturingDate)
                .manufacturingTime(manufacturingTime)
                .dueDate(dueDate)
                .build();
    }

    /**
     * Method that helps to create a static batchStock object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @return returns a static batch stock
     */
    private static BatchStockDTO sameBatchStockGenerator() {
        return BatchStockDTO.builder()
                .batchNumber(1)
                .productId("String")
                .currentTemperature(10.0f)
                .minimumTemperature(10.0f)
                .initialQuantity(3)
                .currentQuantity(3)
                .manufacturingDate(LocalDate.of(2021, 10, 10))
                .manufacturingTime(LocalDateTime.of(2021, 10, 10, 8, 36))
                .dueDate(LocalDate.of(2021, 10, 10))
                .build();
    }

    /**
     * Method that creates a payload for Inbound Order
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @return returns an Inbound Order payload
     */
    public static InboundOrderDTO inboundOrderDTOGenerator() {
        BatchStockDTO batchStock1 = sameBatchStockGenerator();
        BatchStockDTO batchStock2 = sameBatchStockGenerator();
        List<BatchStockDTO> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);

        return InboundOrderDTO.builder()
                .sectionCode("String")
                .orderNumber(1)
                .orderDate(LocalDate.of(2022, 10, 10))
                .warehouseCode("String")
                .batchStocks(batchStockList).build();
    }

    /**
     * Method that helps to create a static inboundOrder object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Oliveira
     * @author Rebecca Cruz
     * @return returns a static inbound order
     */
    public static InboundOrder inboundOrderGenerator () {
        BatchStockDTO batchStock1 = sameBatchStockGenerator();
        BatchStockDTO batchStock2 = sameBatchStockGenerator();
        List<BatchStockDTO> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);
        List<BatchStock> batches = IBatchStockMapper.MAPPER.mapDTO(batchStockList);

        return InboundOrder.builder()
                .orderNumber(1)
                .orderDate(LocalDate.of(2020, 3, 10))
                .representant(WarehouseUtil.representantGenerator())
                .section(WarehouseUtil.sectionGenerator())
                .batchStocks(batches)
                .warehouse(WarehouseUtil.emptywarehouseGenerator())
                .build();
    }

    /**
     * Method that helps to create a static inbound order object order number id to be used on Inbound Order payload
     * @author Rebecca Cruz
     * @author Evelyn Cristini
     * @author Lucas Pinheiro
     * @return returns a static inboundOrder
     */
    public static InboundOrder emptyInboundOrder () {
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setOrderNumber(1);

        return inboundOrder;
    }
}
