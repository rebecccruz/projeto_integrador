package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class SessionTestUtil {
    public static Section generateSessionModel () {
        Section model = Section.builder().build();
        return model;
    }

    public static Section sectionSampleOne() {
        Warehouse warehouse = WarehouseTestUtil.emptyWarehouseGenerator();
        Section section = returnEmptySectionSampleOne();
        section.setWarehouse(warehouse);
        section.setBatchStocks(BatchStocksTestUtil.listOfBatchStock());
        section.setCapacity(50);
        section.setCategory(Category.FS);
        return section;
    }

    public static List<Section> getListOfSections() {
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(sectionSampleOne());
        return sectionList;
    }

    public static Section returnEmptySectionSampleOne() {
        Section section = generateSessionModel();
        section.setId(1L);
        section.setCode("FS");
        return section;
    }
}
