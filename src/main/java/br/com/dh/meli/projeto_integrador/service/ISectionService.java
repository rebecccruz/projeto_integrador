package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;

import java.util.List;

public interface ISectionService {
    List<Section> findAllByCategory(Category category);
}
