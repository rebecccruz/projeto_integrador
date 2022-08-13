package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.exception.PreconditionFailedException;
import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import br.com.dh.meli.projeto_integrador.repository.IWarehouseRepository;
import br.com.dh.meli.projeto_integrador.util.WarehouseUtil;
import org.junit.jupiter.api.Assertions;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WarehouseServiceTest {

    @InjectMocks
    WarehouseService service;

    @Mock
    IWarehouseRepository repo;

    @BeforeEach
    public void setup(){

        BDDMockito
                .when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(WarehouseUtil.warehouseGenerator()));

        BDDMockito
                .when(repo.findWarehouseByCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(WarehouseUtil.warehouseGenerator()));
    }

    @Test
    @DisplayName("Find warehouse by id")
    void findWarehouseById_whenIdExists() {
        long id = 1;
        Warehouse warehouse = service.findWarehouseById(id);

        assertThat(warehouse.getId()).isEqualTo(WarehouseUtil.warehouseGenerator().getId());
    }

    @Test
    @DisplayName("Find warehouse by id when warehouse doesnt exist")
    void findWarehouseById_whenIdDoesNotExist() {
        long id = 10;
        Warehouse warehouse = service.findWarehouseById(id);

        BDDMockito
                .when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.findWarehouseById(id));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(warehouse.getId()).isEqualTo(WarehouseUtil.warehouseGenerator().getId());
    }

    @Test
    @DisplayName("Find warehouse by code")
    void findWarehouseByCode_whenCodeExists() {
        Warehouse warehouse = service.findWarehouseByCode("MLB-SP");

        assertThat(warehouse.getCode()).isEqualTo(WarehouseUtil.warehouseGenerator().getCode());
    }

    @Test
    @DisplayName("Find warehouse by code when warehouse doesnt exist")
    void findWarehouseByCode_whenCodeDoesNotExist() {
        Warehouse warehouse = service.findWarehouseByCode("MLB-RJ");

        BDDMockito
                .when(repo.findWarehouseByCode(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        PreconditionFailedException exception = Assertions.assertThrows(
                PreconditionFailedException.class, () -> service.findWarehouseByCode("MLB-RJ"));

        assertThat(warehouse.getCode()).isNotEqualTo(WarehouseUtil.emptywarehouseGenerator());
        assertThat(exception.getMessage()).isEqualTo("Warehouse code not found");
    }

    @Test
    @DisplayName("Find representant from warehouse")
    void findRepresentantFromWarehouse() {
        Warehouse warehouse = WarehouseUtil.warehouseGenerator();
        List<Representant> representant = warehouse.getRepresentants();

        assertThat(warehouse.getRepresentants()).isEqualTo(WarehouseUtil.warehouseGenerator().getRepresentants());
    }

    @Test
    @DisplayName("Find representant from warehouse when representant does not exist")
    void findRepresentantFromWarehouse_whenRepresentantDoesNotExist() {
        Warehouse warehouse = WarehouseUtil.emptywarehouseGenerator();

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> {

                }
        );

        assertThat(exception.getMessage()).isEqualTo("invalid representId");
    }

    @Test
    void getBatchStockByFilter(){


    }


}
