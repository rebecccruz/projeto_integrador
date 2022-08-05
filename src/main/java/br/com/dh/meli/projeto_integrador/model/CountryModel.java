package br.com.dh.meli.projeto_integrador.model;
import lombok.Builder;
import lombok.Data;

/**
 * Country Entitie
 *
 * @author Alexandre Borges Souza
 */
@Data
@Builder
public class CountryModel {
    private Long id;
    private String name;
    private String initials;
}
