package br.com.dh.meli.projeto_integrador.enums;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import br.com.dh.meli.projeto_integrador.service.IBatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

public enum ParamOrderBy {

    BATCH_NUMBER("L") {
        @Override
        public List<BatchStock> findAllBatchStocksSorted(String productId, Section section) {
            return getRepository().findBatchStocksByProductIdAndSectionOrderByBatchNumberAsc(productId, section);
        }
    },
    CURRENT_QUANTITY("Q") {
        @Override
        public List<BatchStock> findAllBatchStocksSorted(String productId, Section section) {
            return getRepository().findBatchStocksByProductIdAndSectionOrderByCurrentQuantityAsc(productId, section);
        }
    },
    DUE_DATE("V") {
        @Override
        public List<BatchStock> findAllBatchStocksSorted(String productId, Section section) {
            return getRepository().findBatchStocksByProductIdAndSectionOrderByDueDateAsc(productId, section);
        }
    };

    /**
     * Inject all service into enum
     *
     * @author Isaias Finger
     */
    @Component
    public static class OrderByServiceInjector {

        @Autowired
        IBatchStockService service;

        @Autowired
        IBatchStockRepository repository;

        @PostConstruct
        public void postConstruct() {
            Arrays.stream(ParamOrderBy.values()).forEach(o -> {
                o.setService(service);
                o.setRepository(repository);
            });
        }
    }

    @Getter
    private String code;

    @Getter
    @Setter
    private IBatchStockService service;

    @Getter
    @Setter
    private IBatchStockRepository repository;

    ParamOrderBy(String code) {
        this.code = code;
    }

    /**
     * Find all BatchStocks filtered by Section and Order
     *
     * @param productId
     * @param section
     * @return List<BatchStock>
     * @author Larissa Navarro, Isaias Finger
     */
    public abstract List<BatchStock> findAllBatchStocksSorted(String productId, Section section);

    /**
     * Wrapper for convert from code into enum
     *
     * @param code
     * @return ParamOrderBy
     * @author Isaias Finger
     */
    public static ParamOrderBy valueOfByCode(String code) {
        ParamOrderBy order;
        try {
            order = Arrays.stream(ParamOrderBy.values())
                    .filter(o -> o.getCode().equalsIgnoreCase(code))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            throw new BadRequestException("invalid orderCode");
        }
        return order;
    }
}
