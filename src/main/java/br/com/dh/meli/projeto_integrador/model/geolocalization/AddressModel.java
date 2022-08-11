package br.com.dh.meli.projeto_integrador.model.geolocalization;

import br.com.dh.meli.projeto_integrador.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


/**
 * Geolocalization/Address Entity
 *
 * @author Evelyn Cristini Oliveira / Alexandre Borges Souza
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id", nullable = false)
    private ZipCodeModel zip;
}
