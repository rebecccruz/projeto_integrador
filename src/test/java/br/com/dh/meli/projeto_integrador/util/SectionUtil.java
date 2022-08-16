package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class SectionUtil {

    public static Section sectionGenerator(Warehouse warehouse, List<BatchStock> batches) {
        Section section = new Section();
        section.setId(1L);
        section.setCode("FF");
        section.setCategory(Category.FF);
        section.setCapacity(100);
        section.setBatchStocks(batches);
        section.setWarehouse(warehouse);
        return section;
    }

    public static Section sectionGenerator() {
        return sectionGenerator(
                WarehouseTestUtil.emptyWarehouseGenerator(),
                BatchStocksTestUtil.listOfBatchStock());
    }

    public static List<Section> sectionListGenerator() {
        List<Section> sections = new ArrayList();
        sections.add(sectionGenerator());
        return sections;
    }
}
