package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.model.Representant;
import java.util.ArrayList;
import java.util.List;

public class RepresentantTestUtil {

    public static Representant generateRepresentantModel() {
        Representant model = Representant.builder().build();
        return model;
    }

    public static Representant representantSampleOne() {
        Representant representant = generateRepresentantModel();
        representant.setId(1L);
        representant.setWarehouse(WarehouseTestUtil.emptyWarehouseGenerator());
        return representant;
    }

    public static List<Representant> listOfRepresentantsSample() {
        List<Representant> representantList = new ArrayList<>();
        representantList.add(representantSampleOne());
        return representantList;
    }
}
