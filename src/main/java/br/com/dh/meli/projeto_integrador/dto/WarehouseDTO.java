package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for payload for add new Warehouse
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    @NotNull
    private String warehouseCode;
    @NotEmpty(message = "O campo Address não pode estar vazio.")
    private AddressModel address;
    @NotEmpty(message = "A lista de Representant deve ter pelo menos 1 válido.")
    public List<@Valid Representant> representant;
    @NotEmpty(message = "A lista de Seller deve ter pelo menos 1 representante válido.")
    private List<@Valid Seller> seller;
    @NotEmpty(message = "A lista de Section deve ter pelo menos 1 válido.")
    private List<@Valid Section> section;
}
