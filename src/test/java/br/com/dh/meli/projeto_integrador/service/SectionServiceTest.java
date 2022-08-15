package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SectionDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.enums.ParamOrderBy;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import br.com.dh.meli.projeto_integrador.repository.ISectionRepository;
import br.com.dh.meli.projeto_integrador.util.BatchStocksTestUtil;
import br.com.dh.meli.projeto_integrador.util.SectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SectionServiceTest {

    @InjectMocks
    private SectionService service;

    @Mock
    private ISectionRepository repo;

    @Mock
    private IBatchStockRepository batchStockRepo;

    @Mock
    private IBatchStockService batchStockService;

    @Mock
    private ParamOrderBy paramOrder;

    @BeforeEach
    public void setup() {
        ParamOrderBy.BATCH_NUMBER.setService(batchStockService);
        ParamOrderBy.BATCH_NUMBER.setRepository(batchStockRepo);

        BDDMockito.when(repo.findSectionsByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(SectionUtil.sectionListGenerator());

        BDDMockito.when(repo.findSectionsByCode(ArgumentMatchers.anyString()))
                .thenReturn(SectionUtil.sectionListGenerator());

        BDDMockito.when(repo.findSectionsByProductId(ArgumentMatchers.anyString()))
                .thenReturn(SectionUtil.sectionListGenerator());

        BDDMockito.when(paramOrder.findAllBatchStocksSorted(ArgumentMatchers.anyString(), ArgumentMatchers.any(Section.class)))
                .thenReturn(BatchStocksTestUtil.listOfBatchStock());
    }

    @Test
    void findAllByCategory_When_isNotEmpty() {
        List<Section> sections = service.findAllByCategory(Category.FF);

        assertThat(sections).isNotEmpty();
    }

    @Test
    void findAllByCategory_When_isEmpty() {
        BDDMockito.when(repo.findSectionsByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.findAllByCategory(Category.FF));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void findAllByCode_When_isNotEmpty() {
        List<Section> sections = service.findAllByCode("FF");

        assertThat(sections).isNotEmpty();
    }

    @Test
    void findAllByCode_When_isEmpty() {
        BDDMockito.when(repo.findSectionsByCode(ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.findAllByCode("FF"));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void findAllByProductIdAndSort_When_isNotEmpty() {
        List<SectionDTO> list = service.findAllByProductIdAndSort("Leite", Optional.empty());

        assertThat(list).isNotEmpty();
    }

    @Test
    void findAllByProductIdAndSort_When_isEmpty() {
        BDDMockito.when(repo.findSectionsByProductId(ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.findAllByProductIdAndSort("Leite", Optional.empty()));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
