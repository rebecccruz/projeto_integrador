package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.model.Warehouse;

public class WarehouseTestUtil {

    public static Warehouse generateWarehouseModel () {
        Warehouse  warehouse = Warehouse.builder().build();
        return warehouse;
    }

    public static Warehouse warehouseModelSampleOne() {
        Warehouse warehouse = emptyWarehouseGenerator();
        warehouse.setInboundOrders(InboundOrderTestUtil.inboundOrderList());
        warehouse.setRepresentants(RepresentantTestUtil.listOfRepresentantsSample());
        warehouse.setSections(SessionTestUtil.getListOfSections());
        return warehouse;
    }

    public static Warehouse emptyWarehouseGenerator () {
        Warehouse warehouse = generateWarehouseModel();
        warehouse.setId(1L);
        warehouse.setCode("MLB-SP");
        return warehouse;
    }
}
