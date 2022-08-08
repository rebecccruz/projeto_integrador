package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.Address;
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
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    public Long id;
    @NotEmpty(message = "O campo sellerName não pode estar vazio.")
    public String name;
    @NotEmpty(message = "A classe Address não pode estar vazio.")
    public Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
