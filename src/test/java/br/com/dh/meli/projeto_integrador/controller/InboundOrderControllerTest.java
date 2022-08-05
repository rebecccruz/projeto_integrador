package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.InboundOrderDTO;
import br.com.dh.meli.projeto_integrador.model.InboundOrder;
import br.com.dh.meli.projeto_integrador.util.InboundOrderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @DisplayName("Create new batchStocks when payload is valid")
    @Test
    void createBatchStock() {
        InboundOrder payload = InboundOrderUtil.inboundOrderGenerator();

        ResponseEntity<InboundOrderDTO> response = controller.createBatchStock(payload);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getBatchStock().size())
                .isEqualTo(payload.getBatchStock().size());
    }

    @DisplayName("Fully update batchStocks when payload is valid")
    @Test
    void updateBatchStock() {
        InboundOrder payload = InboundOrderUtil.inboundOrderGenerator();

        ResponseEntity<InboundOrderDTO> response = controller.updateBatchStock(payload);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getBatchStock().size())
                .isEqualTo(payload.getBatchStock().size());
    }
}