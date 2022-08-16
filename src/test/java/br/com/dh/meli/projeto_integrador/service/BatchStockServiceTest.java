package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.repository.IBatchStockRepository;
import br.com.dh.meli.projeto_integrador.util.BatchStocksTestUtil;
import br.com.dh.meli.projeto_integrador.util.InboundOrderTestUtil;
import br.com.dh.meli.projeto_integrador.util.SectionUtil;
import br.com.dh.meli.projeto_integrador.util.WarehouseTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Batch Stock Service Test
 *
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchStockServiceTest {

    @InjectMocks
    private BatchStockService service;

    @Mock
    private IBatchStockRepository repo;

    /**
     * Successfully convert list of batchStockDTO to BatchStock
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void batchStockMapper_returnSuccessfully_whenBatchStockMapper() {
        List<BatchStockDTO> batchStockList = BatchStocksTestUtil.payloadForInboundOrderPayload();
        InboundOrder inboundOrder = InboundOrderTestUtil.inboundOrderSampleOne();
        List<BatchStock> result = service.batchStockMapper(batchStockList, inboundOrder);
        assertThat(result.size()).isPositive();
    }

    /**
     * Successfully save all batches stocks
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void saveAll() {
        List<BatchStock> batches = BatchStocksTestUtil.listOfBatchStock();
        when(repo.saveAll(ArgumentMatchers.any())).thenReturn(batches);
        List<BatchStock> result = service.saveAll(batches);
        assertFalse(result.isEmpty());
        assertThat(result.size()).isPositive();
        verify(repo, atLeastOnce()).saveAll(batches);
    }

    /**
     * Successfully test on find batch stock by product ID
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findByProductId_returnBatchStock_whenFindByBatchStockProductIDFound() {
        String productId = BatchStocksTestUtil.batchStockDTOSampleOne().getProductId();
        when(repo.findBatchStockByProductId(ArgumentMatchers.anyString())).thenReturn(Optional.of(BatchStocksTestUtil.batchStockSampleOneByDTO()));
        BatchStock result = service.findByProductId(productId);
        assertThat(result.getBatchNumber()).isPositive();
        verify(repo , times(1)).findBatchStockByProductId(productId);
    }

    /**
     * Faliure test on find batch stock by product ID not exist and generate Exception
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findByProductId_returnThrows_whenFindByBatchStockProductIDNotFound() {
        String productId = BatchStocksTestUtil.batchStockDTOSampleOne().getProductId();
        when(repo.findBatchStockByProductId(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(null));
        NotFoundException exception = assertThrows(NotFoundException.class , () -> {
            service.findByProductId(productId);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("productId not found"));
    }

    /**
     * Successfully test on find batch stock by number
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findByBatchNumber_returnBatchStock_whenFindByBatchStockNumberFound() {
        Integer batchNumber = BatchStocksTestUtil.batchStockDTOSampleOne().getBatchNumber();
        when(repo.findBatchStockByBatchNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.of(BatchStocksTestUtil.batchStockSampleOneByDTO()));
        BatchStock result = service.findByBatchNumber(batchNumber);
        assertThat(result.getBatchNumber()).isPositive();
        verify(repo ,atLeastOnce()).findBatchStockByBatchNumber(batchNumber);
    }

    /**
     * Faliure test on find batch stock by number
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findByBatchNumber_returnThrows_whenFindByBatchStockNumberNotFound() {
        Integer batchNumber = BatchStocksTestUtil.batchStockDTOSampleOne().getBatchNumber();
        when(repo.findBatchStockByBatchNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null));
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class , () -> {
            service.findByBatchNumber(batchNumber);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("batch doesn't exists"));
    }

    /**
     * Successfully test faliure on find batch stock by number is exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void batchNumberExistenceValidation_returnThrows_whenFindByBatchStockNumberExist() {
        Integer batchNumber = BatchStocksTestUtil.batchStockDTOSampleOne().getBatchNumber();
        when(repo.findBatchStockByBatchNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(BatchStocksTestUtil.batchStockSampleOneByDTO()));
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class , () -> {
            service.batchNumberExistenceValidation(batchNumber);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("batch already exists"));
    }

    /**
     * Successfully test on find batch stock by product ID is exist
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void countStocksByProductId() {
        String productId = BatchStocksTestUtil.batchStockDTOSampleOne().getProductId();
        when(repo.existsBatchStocksByProductId(ArgumentMatchers.anyString())).thenReturn(true);
        service.countStocksByProductId(productId);
        verify(repo, atLeastOnce()).existsBatchStocksByProductId(productId);
    }

    /**
     * Faliure test on find batch stock by product ID is not exist and return throws
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void countStocksByProductId_returnThrows_whenProductIDNotExist() {
        String productId = BatchStocksTestUtil.batchStockDTOSampleOne().getProductId();
        when(repo.existsBatchStocksByProductId(ArgumentMatchers.anyString())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.countStocksByProductId(productId);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("productId not found"));
    }

    /**
     * Successfully test on find batch stock by product ID
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findAllByProductId_returnListOfBatchStock_whenFindBatchStockExist() {
        String productId = BatchStocksTestUtil.batchStockDTOSampleOne().getProductId();
        when(repo.findBatchStocksByProductId(ArgumentMatchers.anyString())).thenReturn(BatchStocksTestUtil.listOfBatchStock());
        List<BatchStock> result = service.findAllByProductId(productId);
        assertThat(result.size()).isNotNull();
        verify(repo, atLeast(1)).findBatchStocksByProductId(productId);
    }

    /**
     * Successfully test on convert batch stock list to DTOs
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void toDTOs_returnListOfBatchStockDTO() {
        List<BatchStockDTO> result = service.toDTOs(BatchStocksTestUtil.listOfBatchStock());
        assertThat(result.size()).isPositive();
    }

    /**
     * Successfully test on list of batch stock order asc
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findAllBySectionsOrderByDueDate_returnListOfBatchStock_whenSuccefullyFindStockBySectiobOrderDueDateAsc() {
        List<Section> sections = SectionUtil.sectionListGenerator();
        when(repo.findBatchStocksBySectionOrderByDueDateAsc(ArgumentMatchers.any())).thenReturn(BatchStocksTestUtil.listOfBatchStock());
        List<BatchStock> list = new ArrayList<>();
        list = service.findAllBySectionsOrderByDueDate(sections);
        assertThat(list.size()).isPositive();
        verify(repo, atLeast(1)).findBatchStocksBySectionOrderByDueDateAsc(ArgumentMatchers.any(Section.class));
    }

    /**
     * Faliure test on find batch stock by empty sections
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findAllBySectionsOrderByDueDate_returnThrows_whenFaliureFindStockBySectionOrderDueDateAsc() {
        List<Section> sections = new ArrayList<>();
        when(repo.findBatchStocksBySectionOrderByDueDateAsc(ArgumentMatchers.any())).thenReturn(BatchStocksTestUtil.listOfBatchStock());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findAllBySectionsOrderByDueDate(sections);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("empty list not found any result"));
    }

    /**
     * Successfully test on find batch stock by empty sections and limit date
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findAllBySectionsAndByDueDateLessThan_returnListOfBatchStock_whenLimitDateIsValid() {
        List<Section> sections = SectionUtil.sectionListGenerator();
        LocalDate limitDate = LocalDate.of(2022,10,1);
        when(repo.findBatchStocksBySectionAndDueDateLessThanOrderByDueDateAsc(ArgumentMatchers.any(), ArgumentMatchers.any(LocalDate.class))).thenReturn(BatchStocksTestUtil.listOfBatchStock());
        List<BatchStock> list = new ArrayList<>();
        list = service.findAllBySectionsAndByDueDateLessThan(sections, limitDate);
        assertThat(list.size()).isPositive();
        verify(repo, atLeast(1))
                .findBatchStocksBySectionAndDueDateLessThanOrderByDueDateAsc(ArgumentMatchers.any(Section.class), ArgumentMatchers.any(LocalDate.class));
    }

    /**
     * Faliure test on find batch stock by empty sections and limit date
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void findAllBySectionsAndByDueDateLessThan_returnThrows_whenLimitDateIsInvalid() {
        List<Section> sections = new ArrayList<>();
        LocalDate limitDate = LocalDate.of(2022,10,1);
        when(repo.findBatchStocksBySectionAndDueDateLessThanOrderByDueDateAsc(ArgumentMatchers.any(), ArgumentMatchers.any(LocalDate.class))).thenReturn(BatchStocksTestUtil.listOfBatchStock());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findAllBySectionsAndByDueDateLessThan(sections, limitDate);
        });
        assertFalse(exception.getMessage().isEmpty());
        assertTrue(exception.getMessage().equalsIgnoreCase("empty list not found any result"));
    }

    /**
     * Successfully test decrease quantify product
     *
     * @author Alexandre Borges Souza
     */
    @Test
    void decreaseQuantity() {
        BatchStock batchStock = BatchStocksTestUtil.batchStockSampleOneByDTO();
        when(repo.save(ArgumentMatchers.any(BatchStock.class))).thenReturn(batchStock);
        Integer currentQuantity = batchStock.getCurrentQuantity();
        BatchStock result = service.decreaseQuantity(batchStock, 5);
        assertTrue(result.getCurrentQuantity() < currentQuantity);
    }
}
