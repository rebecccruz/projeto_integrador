package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.Address;

import javax.validation.constraints.NotEmpty;

/**
 * Representant Entity
 *
 * @author Evelyn Cristini Oliveira
 */
public class Representant {

    public Long id;
    @NotEmpty(message = "O campo representantName não pode estar vazio.")
    public String name;
    @NotEmpty(message = "A classe Address não pode estar vazio.")
    public Address address;
}
