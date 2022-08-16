package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.model.Warehouse;

/**
 * Warehouse Test Utility
 *
 * @author Lucas Pinheiro Rocha
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
public class WarehouseTestUtil {

    /**
     * Generate empty Warehouse
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     * @return Warehouse
     */
    public static Warehouse generateWarehouseModel () {
        Warehouse  warehouse = Warehouse.builder().build();
        return warehouse;
    }

    /**
     * Generate Warehouse with content
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     * @return Warehouse
     */
    public static Warehouse warehouseModelSampleOne() {
        Warehouse warehouse = emptyWarehouseGenerator();
        warehouse.setInboundOrders(InboundOrderTestUtil.inboundOrderList());
        warehouse.setRepresentants(RepresentantTestUtil.listOfRepresentantsSample());
        warehouse.setSections(SessionTestUtil.getListOfSections());
        return warehouse;
    }

    /**
     * Generate Warehouse with ID and code
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     * @return Warehouse
     */
    public static Warehouse emptyWarehouseGenerator () {
        Warehouse warehouse = generateWarehouseModel();
        warehouse.setId(1L);
        warehouse.setCode("MLB-SP");
        return warehouse;
    }
}
