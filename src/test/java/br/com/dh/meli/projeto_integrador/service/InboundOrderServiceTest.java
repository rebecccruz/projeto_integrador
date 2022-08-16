package br.com.dh.meli.projeto_integrador.service;

import java.util.Optional;
import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.repository.IInboundOrderRepository;
import br.com.dh.meli.projeto_integrador.util.InboundOrderTestUtil;
import br.com.dh.meli.projeto_integrador.util.WarehouseTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

/**
 * Test Inbound Service
 * @author Lucas Pinheiro Rocha
 * @author Alexandre Borges Souza
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService service;

    @Mock
    private IInboundOrderRepository repo;

    @Mock
    private IBatchStockService batchStockService;

    @Mock
    private IWarehouseService warehouseService;

    /**
     * Successfully test to create Inbound Order with valid contents
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Create new Inbound Order")
    void createInboundOrder_returnSuccessfullyCreate_whenValidContents() {
        willDoNothing().given(batchStockService).batchNumberExistenceValidation(ArgumentMatchers.anyInt());
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null));
        when(warehouseService.findWarehouseByCode(ArgumentMatchers.anyString())).thenReturn(WarehouseTestUtil.warehouseModelSampleOne());
        when(repo.save(ArgumentMatchers.any(InboundOrder.class))).thenReturn(InboundOrderTestUtil.inboundOrderSampleOne());
        InboundOrderDTO inboundOrderDTO = InboundOrderTestUtil.inboundOrderDTOPayloadOne();
        InboundOrder result = service.createInboundOrder(inboundOrderDTO);
        assertThat(result.getOrderNumber()).isPositive();
        assertThat(result.getBatchStocks().size()).isNotNull();
        verify(repo, times(1)).save(ArgumentMatchers.any(InboundOrder.class));
    }

    /**
     * Faliure test to create Inbound Order when inbound order number exist
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Return throw when inbound order exist")
    void createInboundOrder_returnthrown_whenInvalidContents() {
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.of(InboundOrder.builder().orderNumber(1).build()));
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class, () -> {
            InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder().orderNumber(1).build();
           service.createInboundOrder(inboundOrderDTO);
        });
        assertFalse(exception.getMessage().isEmpty());
    }

    @Test
    @DisplayName("Convert InboundOrder to InboundOrderDTO")
    void convertToDto() {
        InboundOrder inboundOrder = InboundOrderTestUtil.inboundOrderSampleOne();
        InboundOrderDTO result = service.convertToDto(inboundOrder);
        assertThat(result.getOrderNumber()).isPositive();
    }

    /**
     * Successfully test to update Inbound Order when inbound order number exist
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    @Test
    @DisplayName("Successfullu updated Inbounder Order")
    void updateInboundOrder_returnSuccessfullyUpdated_whenValidContents() {
        InboundOrderDTO payload = InboundOrderTestUtil.inboundOrderDTOPayloadOne();
        InboundOrder inboundOrder = InboundOrderTestUtil.inboundOrderByPayloadParam(payload);
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.of(inboundOrder));
        when(warehouseService.findWarehouseByCode(ArgumentMatchers.anyString())).thenReturn(WarehouseTestUtil.warehouseModelSampleOne());
        when(repo.save(ArgumentMatchers.any(InboundOrder.class))).thenReturn(inboundOrder);
        InboundOrder result = service.updateInboundOrder(payload);
        assertThat(result.getOrderNumber()).isPositive();
        assertThat(result.getBatchStocks().size()).isNotNull();
        verify(repo, Mockito.times(1)).save(ArgumentMatchers.any(InboundOrder.class));
    }

    @Test
    @DisplayName("Update InboundOrder")
    void updateInboundOrder_returnthrown_whenInvalidContents() {
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null));
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder().orderNumber(1).build();
            service.updateInboundOrder(inboundOrderDTO);
        });
        assertFalse(exception.getMessage().isEmpty());
    }
}
