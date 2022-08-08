package br.com.dh.meli.projeto_integrador.dto;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * DTO for payload for add new Seller
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
    @NotNull(message = "Nome do vendedor é obrigatório")
    @JsonProperty("seller_name")
    public String name;
    @NotNull(message = "Endereço do vendedor é obrigatório")
    @JsonProperty("seller_address")
    public AddressModel address;
}
