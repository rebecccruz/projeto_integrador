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
    @NotNull
    private Integer batchNumber;
    @NotNull
    private Float currentTemperature;
    @NotNull
    private Float minimumTemperature;
    @NotNull
    private Integer initialQuantity;
    @NotNull
    private Integer currentQuantity;
    @NotNull
    private LocalDate manufacturingDate;
    @NotNull
    private LocalDateTime manufacturingTime;
    @NotNull
    private LocalDate dueDate;
    @NotEmpty(message = "O campo category não pode estar vazio.")
    @Size(max = 2, message = "O campo category deve ter no máximo 2 digitos.")
    private Category category;
}
