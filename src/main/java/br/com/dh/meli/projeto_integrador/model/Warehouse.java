package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Warehouse Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
public class Warehouse {
    private Long id;
    private String warehouseCode;
    private AddressModel address;
    public List<Representant> representant;
    private List<Seller> seller;
    private List<Section> section;
}
