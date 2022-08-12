package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SectionService implements ISectionService {

    @Autowired
    ISectionRepository repo;

    @Override
    public List<Section> findAllByCategory(Category category) {
        return repo.findSectionsByCategory(category);
    }

}
