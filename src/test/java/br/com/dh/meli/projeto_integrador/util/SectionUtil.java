package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import java.util.ArrayList;
import java.util.List;

public class SectionUtil {

    public static Section sectionGenerator() {
        Section section = new Section();
        section.setId(1L);
        section.setCode("FF");
        section.setCategory(Category.FF);
        section.setCapacity(100);
        section.setBatchStocks(BatchStocksTestUtil.listOfBatchStock());
        section.setWarehouse(WarehouseTestUtil.emptyWarehouseGenerator());
        return section;
    }

    public static List<Section> sectionListGenerator() {
        List<Section> sections = new ArrayList();
        sections.add(sectionGenerator());
        return sections;
    }
}
