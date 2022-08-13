package br.com.dh.meli.projeto_integrador.service;

import java.util.Optional;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.repository.IInboundOrderRepository;
import br.com.dh.meli.projeto_integrador.util.InboundOrderTestUtil;
import br.com.dh.meli.projeto_integrador.util.InboundOrderUtil;
import br.com.dh.meli.projeto_integrador.util.WarehouseTestUtil;
import ch.qos.logback.core.encoder.EchoEncoder;
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
    void createInboundOrder_returnSuccessfullyCreate_whenValidContents() {
        willDoNothing().given(batchStockService).batchNumberExistenceValidation(ArgumentMatchers.anyInt());
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null));
        when(warehouseService.findWarehouseByCode(ArgumentMatchers.anyString())).thenReturn(WarehouseTestUtil.warehouseModelSampleOne());
        when(repo.save(ArgumentMatchers.any(InboundOrder.class))).thenReturn(InboundOrderTestUtil.inboundOrderSampleOne());
        InboundOrderDTO inboundOrderDTO = InboundOrderTestUtil.inboundOrderDTOPayloadOne();
        InboundOrder result = service.createInboundOrder(inboundOrderDTO);
        System.out.printf(result.toString());
        assertThat(result.getOrderNumber()).isPositive();
        assertThat(result.getBatchStocks().size()).isNotNull();
    }

    /**
     * Faliure test to create Inbound Order with invalid contents and generate throws
     *
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    @Test
    void createInboundOrder_returnthrown_whenInvalidContents() {
        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.of(InboundOrder.builder().orderNumber(1).build()));
        PreconditionFailedException exception = assertThrows(PreconditionFailedException.class, () -> {
            InboundOrderDTO inboundOrderDTO = InboundOrderDTO.builder().orderNumber(1).build();
           service.createInboundOrder(inboundOrderDTO);
        });
        assertFalse(exception.getMessage().isEmpty());
    }

    @Test
    void convertToDto() {
    }

    @Test
    void updateInboundOrder() {
    }
}
