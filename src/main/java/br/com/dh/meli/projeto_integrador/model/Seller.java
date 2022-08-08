package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Seller Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
public class Seller {
    public Long id;
    public String name;
    public AddressModel address;
}
