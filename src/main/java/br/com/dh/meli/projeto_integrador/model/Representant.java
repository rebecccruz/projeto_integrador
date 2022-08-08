package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Representant Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Entity
public class Representant extends Person{
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("representant")
    private Warehouse warehouse;
}
