package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SectionDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SectionService implements ISectionService {

    @Autowired
    ISectionRepository repo;

    @Autowired
    IBatchStockService batchStockService;

    @Override
    public List<Section> findAllByCategory(Category category) {
        List<Section> sections = repo.findSectionsByCategory(category);
        if (sections.isEmpty()) {
            throw new NotFoundException("sections not found by this category");
        }
        return sections;
    }

    @Override
    public List<Section> findAllByCode(String code) {
        List<Section> sections = repo.findSectionsByCode(code);
        if (sections.isEmpty()) {
            throw new NotFoundException("sectionCode not found");
        }
        return sections;
    }

    @Override
    public List<SectionDTO> findAllByProductIdAndSort(String productId, Optional<ParamOrderBy> order) {
        if (order.isEmpty()) {
            order = Optional.of(ParamOrderBy.BATCH_NUMBER);
        }
        final ParamOrderBy sort = order.get();
        List<SectionDTO> result = new ArrayList<>();
        List<Section> sections = repo.findSectionsByProductId(productId);
        sections.forEach(s -> {
            SectionDTO dto = new SectionDTO();
            dto.setWarehouseCode(s.getWarehouse().getCode());
            dto.setSectionCode(s.getCode());
            dto.setProductId(productId);
            List<BatchStock> sorted = sort.findAllBatchStocksSorted(dto.getProductId(), s);
            dto.setBatchStocks(batchStockService.toDTOs(sorted));
            result.add(dto);
        });
        if (result.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return result;
    }

}
