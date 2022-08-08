package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import lombok.Builder;
import lombok.Data;

/**
 * Representant Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
public class Representant {
    public Long id;
    public String name;
    public AddressModel address;
}
