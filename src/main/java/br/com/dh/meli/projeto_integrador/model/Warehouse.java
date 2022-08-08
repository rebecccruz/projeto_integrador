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
 * @author Evelyn Cristini Oliveira
 */

@Data
@Entity
@NoArgsConstructor
public class Warehouse {
    @Id
    private Long id;

    @Column(name = "warehouse_code", nullable = false)
    private String warehouseCode;
//    private AddressModel address;

//    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("warehouse")
//    private List<Representant> representant;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouse")
    private List<Section> sections;
}
