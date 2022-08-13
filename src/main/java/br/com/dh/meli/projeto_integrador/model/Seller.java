package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Seller Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends Person{
    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<Advertisement> advertisements;
}
