package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.*;

import java.util.ArrayList;
import java.util.List;

public class WarehouseUtil {

    /**
     * Method that helps to create a static warehouse object to be used on Warehouse payload
     * @author Rebecca Cruz
     * @author Evelyn Cristini
     * @return returns a static warehouse
     */
    public static Warehouse warehouseGenerator () {
        Warehouse warehouse = new Warehouse();
        List<InboundOrder> inboundOrder = new ArrayList<>();
        inboundOrder.add(InboundOrderUtil.emptyInboundOrder());
        List<Representant> representant = new ArrayList<>();
        representant.add(representantGenerator());
        List<Section> section = new ArrayList<>();
        section.add(sectionGenerator());

        warehouse.setId(1L);
        warehouse.setCode("MLB-SP");
        warehouse.setRepresentants(representant);
        warehouse.setSections(section);
        warehouse.setInboundOrders(inboundOrder);

        return warehouse;
    }

    /**
     * Method that helps to create a static warehouse object with id to be used on Warehouse payload
     * @author Rebecca Cruz
     * @author Evelyn Cristini
     * @return returns a static warehouse
     */

    public static Warehouse emptywarehouseGenerator () {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);

        return warehouse;
    }

    /**
     * Method that helps to create a static representant object to be used on Warehouse payload
     * @author Rebecca Cruz
     * @author Evelyn Cristini
     * @return returns a static representant
     */

    public static Representant representantGenerator () {
        Representant representant = new Representant();
        representant.setId(1L);
        representant.setName("Evelyn Oliveira");
        representant.setWarehouse(emptywarehouseGenerator());

        return representant;
    }

    /**
     * Method that helps to create a static section object to be used on Warehouse payload
     * @author Rebecca Cruz
     * @author Evelyn Cristini
     * @return returns a static section
     */
    public static Section sectionGenerator () {
        List<BatchStock> batchStock = new ArrayList<>();
        batchStock.add(AdvertisementUtil.batchStockGenerator());
        List<InboundOrder> inboundOrder = new ArrayList<>();
        inboundOrder.add(InboundOrderUtil.emptyInboundOrder());

        Section section = new Section();
        section.setId(1L);
        section.setCode("FS");
        section.setCategory(Category.FS);
        section.setCapacity(100);
        section.setBatchStocks(batchStock);
        section.setInboundOrders(inboundOrder);
        section.setWarehouse(emptywarehouseGenerator());

        return section;
    }
}
