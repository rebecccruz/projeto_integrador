package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface ISectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionsByCategory(Category category);
    List<Section> findSectionsByCode(String code);
    @Query(value = "SELECT a.* " +
            "FROM section a " +
            "INNER JOIN batch_stock b " +
            "ON a.id = b.section_id " +
            "WHERE b.product_sku = :productId " +
            "GROUP BY a.id", nativeQuery = true)
    List<Section> findSectionsByProductId(String productId);
}
