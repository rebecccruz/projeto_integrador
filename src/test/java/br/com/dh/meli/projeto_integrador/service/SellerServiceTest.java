package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.ISellerMapper;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import br.com.dh.meli.projeto_integrador.util.SellerTestUtil;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SellerServiceTest {

    @InjectMocks
    SellerService service;

    @Mock
    ISellerRepository repo;

    @BeforeEach
    public void setup(){
        BDDMockito
                .when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(SellerTestUtil.sellerGenerator()));

        BDDMockito
                .when(repo.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(SellerTestUtil.sellerGenerator());
    }

//    @Test
//    void createSeller() {
//        Seller newSeller = Seller.builder().build();
//        newSeller.setId(1L);
//        newSeller.setName("Teste");
//
//        BDDMockito
//                .when(repo.save(ArgumentMatchers.any(Seller.class)))
//                .thenReturn(newSeller);
//
//        SellerDTO dto = SellerDTO.builder().build();
//        dto.setId(1L);
//        dto.setName("Teste");
//
//        Seller rSeller = service.createSeller(dto);
//        assertThat(rSeller.getName()).isNotNull();
//        verify(repo,atLeastOnce()).save(ArgumentMatchers.any(Seller.class));
//    }

    @Test
    void findSellerById() {
        Long id = 1L;
        Seller seller = service.findSellerById(id);

        assertThat(seller.getId()).isEqualTo(SellerTestUtil.sellerGenerator().getId());
    }

    @Test
    void findSellerById_whenIdDoesNotExist() {
        Long id = 10L;

        BDDMockito
                .when(repo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> service.findSellerById(id));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void saveSeller() {
        Seller seller = SellerTestUtil.sellerGenerator();

        Seller rSeller = service.saveSeller(seller);

        assertThat(rSeller.getId()).isEqualTo(seller.getId());
        verify(repo, atLeastOnce()).save(seller);

    }

    @Test
    void updateSeller() {
        Seller newSeller = SellerTestUtil.sellerGenerator();
        Seller updatedSeller = service.updateSeller(newSeller);

        assertThat(updatedSeller).isNotNull();
        assertThat(updatedSeller.getId()).isEqualTo(newSeller.getId());
    }

    @Test
    void deleteSeller() {
        Seller newSeller = Seller.builder().build();
        newSeller.setId(1L);
        newSeller.setName("Teste");
        SellerDTO dto = SellerDTO.builder().build();
        dto.setName("Teste");
        when(repo.findById(1L)).thenReturn(Optional.of(newSeller));

        willDoNothing().given(repo).deleteById(1L);
        service.deleteSeller(1L);
        //verify(repo, never()).deleteById(1L);
    }
}
