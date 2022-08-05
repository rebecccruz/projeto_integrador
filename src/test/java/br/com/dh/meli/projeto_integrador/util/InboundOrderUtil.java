package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;

import java.util.ArrayList;
import java.util.List;

public class InboundOrderUtil {

    /**
     * Method that helps to create a customized batchStock object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @param sectionCode section code of warehouse
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
    private BatchStock batchStockGenerator(
            String batchNumber,
            String productId,
            String currentTemperature,
            String minimumTemperature,
            String initialQuantity,
            String currentQuantity,
            String manufacturingDate,
            String manufacturingTime
    ) {
        return BatchStock.builder()
                .batchNumber(batchNumber)
                .productId(productId)
                .currentTemperature(currentTemperature)
                .minimumTemperature(minimumTemperature)
                .initialQuantity(initialQuantity)
                .currentQuantity(currentQuantity)
                .manufacturingDate(manufacturingDate)
                .manufacturingTime(manufacturingTime)
                .build();
    }

    /**
     * Method that helps to create a static batchStock object to be used on Inbound Order payload
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @return returns a static batch stock
     */
    private static BatchStock sameBatchStockGenerator() {
        return BatchStock.builder()
                .batchNumber("int")
                .productId("String")
                .currentTemperature("Float")
                .minimumTemperature("Float")
                .initialQuantity("int")
                .currentQuantity("int")
                .manufacturingDate("LocalDate")
                .manufacturingTime("LocalDateTime")
                .dueDate("LocalDate")
                .build();
    }

    /**
     * Method that creates a payload for Inbound Order
     * @author Lucas Pinheiro
     * @author Evelyn Cristini
     * @return returns an Inbound Order payload
     */
    public static InboundOrder inboundOrderGenerator() {
        BatchStock batchStock1 = sameBatchStockGenerator();
        BatchStock batchStock2 = sameBatchStockGenerator();
        List<BatchStock> batchStockList = new ArrayList<>();
        batchStockList.add(batchStock1);
        batchStockList.add(batchStock2);

        return InboundOrder.builder()
                .sectionCode("String")
                .orderNumber("int")
                .orderDate("LocalDate")
                .warehouseCode("String")
                .batchStock(batchStockList).build();
    }
}
