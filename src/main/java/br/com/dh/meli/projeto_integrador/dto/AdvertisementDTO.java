package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDTO {

    private Long id;
    @NotNull
    private String productId;
    @NotNull
    private SellerDTO seller;
    @NotNull
    private Double price;
    @NotNull
    private String description;
}
