package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.service.IInboundOrderService;
import br.com.dh.meli.projeto_integrador.util.InboundOrderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InboundOrderControllerTest {

    @InjectMocks
    private InboundOrderController controller;

    @Mock
    private IInboundOrderService service;

    @BeforeEach
    public void setup() {
        BDDMockito.when(service.createInboundOrder(ArgumentMatchers.any(InboundOrderDTO.class)))
                .thenReturn(InboundOrderUtil.inboundOrderGenerator());

        BDDMockito.when(service.updateInboundOrder(ArgumentMatchers.any(InboundOrderDTO.class)))
                .thenReturn(InboundOrderUtil.inboundOrderGenerator());

        BDDMockito.when(service.convertToDto(ArgumentMatchers.any(InboundOrder.class)))
                .thenReturn(InboundOrderUtil.inboundOrderDTOGenerator());

    }

    @DisplayName("Create new batchStocks when payload is valid")
    @Test
    void createBatchStock() {
        InboundOrderDTO payload = InboundOrderUtil.inboundOrderDTOGenerator();

        ResponseEntity<InboundOrderDTO> response = controller.createInboundOrder(payload);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getBatchStock().size())
                .isEqualTo(payload.getBatchStock().size());
    }

    @DisplayName("Fully update batchStocks when payload is valid")
    @Test
    void updateBatchStock() {
        InboundOrderDTO payload = InboundOrderUtil.inboundOrderDTOGenerator();

        ResponseEntity<InboundOrderDTO> response = controller.updateInboundOrder(payload);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getBatchStock().size())
                .isEqualTo(payload.getBatchStock().size());
    }
}
