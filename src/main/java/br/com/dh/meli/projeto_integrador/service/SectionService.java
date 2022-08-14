package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.SectionDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.IBatchStockMapper;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

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
        List<SectionDTO> result = new ArrayList<>();
        List<BatchStock> allBatches = batchStockService.findAllByProductId(productId);
        List<Section> sections = repo.findAll();
        sections.forEach(s -> {
            SectionDTO dto = new SectionDTO();
            List<BatchStock> wBatches = allBatches.stream()
                    .filter(bf -> bf.getSection().getId().equals(s.getId()))
                    .collect(Collectors.toList());
            wBatches.forEach(b -> {
                dto.setWarehouseCode(b.getSection().getWarehouse().getCode());
                dto.setSectionCode(b.getSection().getCode());
                dto.setProductId(b.getProductId());

                List<BatchStockDTO> batchesDto = IBatchStockMapper.MAPPER.map(
                        batchStockService.findAllByProductIdAndSection(b.getProductId(), b.getSection()));
                if (order.isPresent()) {
                    batchesDto = IBatchStockMapper.MAPPER.map(
                            order.get().findAllBatchStocksSorted(b.getProductId(), b.getSection()));
                }
                dto.setBatchStocks(batchesDto);
            });
            if (!wBatches.isEmpty()) {
                result.add(dto);
            }
        });
        if (result.isEmpty()) {
            throw new NotFoundException("empty list not found any result");
        }
        return result;
    }

}
