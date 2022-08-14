package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ISectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionsByCategory(Category category);
    List<Section> findSectionsByCode(String code);
}
