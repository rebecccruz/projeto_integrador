package br.com.dh.meli.projeto_integrador.model.geolocalization;
import lombok.Builder;
import lombok.Data;

/**
 * Geolocalization/Country Entity
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
