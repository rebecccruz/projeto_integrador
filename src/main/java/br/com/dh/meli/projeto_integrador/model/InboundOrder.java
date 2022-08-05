package br.com.dh.meli.projeto_integrador.model;

import lombok.*;
import javax.validation.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrder {
    @NotNull
    public Integer orderNumber;
    @NotNull
    public LocalDate orderDate;
    @NotEmpty(message = "O campo warehouseCode não pode estar vazio.")
    @Size(min = 2, message = "O campo warehouseCode deve ter no mínimo 2 digitos.")
    public String warehouseCode;
    @NotEmpty(message = "O campo sectionCode não pode estar vazio.")
    @Size(min = 2, message = "O campo sectionCode deve ter no mínimo 2 digitos.")
    public String sectionCode;
    @NotEmpty(message = "A lista de BatchStock deve ter pelo menos 1 válido.")
    public List<@Valid BatchStock> batchStock;
}
