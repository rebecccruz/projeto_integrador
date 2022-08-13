package br.com.dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Representant extends Person {

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("representants")
    private Warehouse warehouse;
}
