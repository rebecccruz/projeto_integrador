package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Test Utility
 *
 * @author Lucas Pinheiro Rocha
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
public class SessionTestUtil {
    /**
     * Generate empty Section Model
     *
     * @return Section
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static Section generateSessionModel () {
        Section model = Section.builder().build();
        return model;
    }

    /**
     * Generate Section with content
     *
     * @return Section
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static Section sectionSampleOne() {
        Warehouse warehouse = WarehouseTestUtil.emptyWarehouseGenerator();
        Section section = returnEmptySectionSampleOne();
        section.setWarehouse(warehouse);
        section.setBatchStocks(BatchStocksTestUtil.listOfBatchStock());
        section.setCapacity(50);
        section.setCategory(Category.FS);
        return section;
    }

    /**
     * Generate a list of Section with content
     *
     * @return List<Section>
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static List<Section> getListOfSections() {
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(sectionSampleOne());
        return sectionList;
    }

    /**
     * Generate Section with ID and code section
     *
     * @return Section
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    public static Section returnEmptySectionSampleOne() {
        Section section = generateSessionModel();
        section.setId(1L);
        section.setCode("FS");
        return section;
    }
}
