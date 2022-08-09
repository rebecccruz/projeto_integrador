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
     * Generate Country with content
     *
     * @return CountryModel
     * @author Alexandre Borges Souza
     */
    public static CountryModel addNewCountry () {
        Date now = new Date();
        CountryModel country = CountryUtil.generateCountryModel();
        country.setName("Brasil");
        country.setInitials("BR");
        return country;
    }
}
