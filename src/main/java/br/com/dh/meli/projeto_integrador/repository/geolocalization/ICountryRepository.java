package br.com.dh.meli.projeto_integrador.repository.geolocalization;

import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Country Repository
 *
 * @author Alexandre Borges Souza
 */
@Repository
public interface ICountryRepository extends JpaRepository<CountryModel, Long> {
}
