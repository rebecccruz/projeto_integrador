package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SectionDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.model.Section;
import java.util.List;
import java.util.Optional;

public interface ISectionService {
    List<Section> findAllByCategory(Category category);
    List<Section> findAllByCode(String code);
    List<SectionDTO> findAllByProductIdAndSort(String productId, Optional<ParamOrderBy> order);
}
