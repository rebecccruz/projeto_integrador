package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Warehouse Entity
 *
 * @author Evelyn Cristini Oliveira / Alexandre Borges Souza
 */

@Data
@Entity
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouse")
    private List<Representant> representants;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouse")
    private List<Section> sections;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouse")
    private List<InboundOrder> inboundOrders;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private AddressModel address;
}
