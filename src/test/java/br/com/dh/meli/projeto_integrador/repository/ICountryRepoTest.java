package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.geolocalization.ICountryRepository;
import br.com.dh.meli.projeto_integrador.util.geolocalization.CountryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * Class Country Repository test
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
@DataJpaTest
@TestPropertySource(locations = "classpath:application-dbmemory.properties")
class ICountryRepoTest {

    @Autowired
    private ICountryRepository repo;

    /**
     * Testing add a new country
     * @author Alexandre Borges Souza
     */
    @Test
    public void add_whenReturnSuccessfulyAdd_whenCountryAddWithContent() {
        CountryModel country = CountryUtil.addNewCountryForH2Database() ;
        CountryModel result = repo.save(country);
        assertTrue(result.getId().intValue()>0);
        assertThat(result.getName()).isEqualTo(country.getName());
        assertThat(result.getInitials()).isEqualTo(country.getInitials());
    }
}
