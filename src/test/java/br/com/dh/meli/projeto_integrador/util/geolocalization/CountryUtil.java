package br.com.dh.meli.projeto_integrador.util.geolocalization;

import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;

import java.util.Date;

/**
 * Class of Util for Country Test
 * @author Alexandre Borges Souza
 * @since 08/08/2022
 */
final public class CountryUtil {

    /**
     * Generate Country Model without content
     *
     * @return CountryModel
     * @author Alexandre Borges Souza
     */
  public static CountryModel generateCountryModel () {
        return CountryModel.builder().build();
    }

    /**
     * Generate Country with content for H2 Database
     *
     * @return CountryModel
     * @author Alexandre Borges Souza
     */
    public static CountryModel addNewCountryForH2Database () {
        CountryModel country = CountryUtil.generateCountryModel();
        country.setName("Brasil");
        country.setInitials("BR");
        return country;
    }

    public static CountryModel addNewCountryWithParamsByMock (CountryModel country) {
        Date now = new Date();
        country.setId(1L);
        country.setCreated_at(now);
        country.setUpdated_at(now);
        return country;
    }

    public static CountryModel getCountry() {
        Date now = new Date();
        CountryModel country = CountryUtil.generateCountryModel();
        country.setId(1L);
        country.setName("Brasil");
        country.setInitials("BR");
        country.setCreated_at(now);
        country.setUpdated_at(now);
        return country;
    }
    public static CountryModel addNewCountryWithoutContentsByMock () {
        CountryModel countryModel = CountryUtil.generateCountryModel();
        countryModel.setId(0L);
        countryModel.setName(null);
        countryModel.setInitials(null);
        countryModel.setCreated_at(null);
        countryModel.setUpdated_at(null);
        return  countryModel;
    }
}
