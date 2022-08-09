package br.com.dh.meli.projeto_integrador.repository.geolocalization;

import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.model.geolocalization.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * State Model Repository
 *
 * @author Alexandre Borges Souza
 */
@Repository
public interface IStateRepository extends JpaRepository<StateModel, Long> {

    /**
     * Count State by country id and state name
     * @param name state name
     * @param countryID country id
     * @return Long
     * @author Alexandre Borges Souza
     */
    @Query(value = "SELECT count(0) FROM state AS s WHERE (s.country_id=:country_id AND s.name like :state_name)", nativeQuery = true)
    Long getCountStateByStateNameId(@Param("state_name") String name, @Param("country_id") Long countryID);

    @Query(value = "SELECT s.id FROM state AS s WHERE (s.country_id=:country_id AND s.name like :state_name)", nativeQuery = true)
    Long getStateIDByStateNameCountryID(@Param("state_name") String name, @Param("country_id") Long countryID);
}
