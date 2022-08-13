package br.com.dh.meli.projeto_integrador.service;

import java.util.Optional;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.repository.IInboundOrderRepository;
import br.com.dh.meli.projeto_integrador.util.InboundOrderUtil;
import com.mysql.cj.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

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
     * @author Lucas Pinheiro Rocha
     * @author Alexandre Borges Souza
     */
    @Test
    void createInboundOrder_returnSuccessfullyCreate_whenValidContents() {
        willDoNothing().given(batchStockService).batchNumberExistenceValidation(ArgumentMatchers.anyInt());

        when(repo.findInboundOrderByOrderNumber(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null));

        Warehouse warehouse = Warehouse.builder().build();
        Representant representant = Representant.builder().build();
        when(warehouseService.findWarehouseByCode(ArgumentMatchers.anyString())).thenReturn(warehouse);
        when(warehouseService.findRepresentantFromWarehouse(ArgumentMatchers.any(Warehouse.class),ArgumentMatchers.anyLong())).thenReturn(representant);

        InboundOrderDTO inboundOrderDTO = InboundOrderUtil.inboundOrderGenerator();
        service.createInboundOrder(inboundOrderDTO);

    }

    @Test
    void convertToDto() {
    }

    @Test
    void updateInboundOrder() {
    }
}
