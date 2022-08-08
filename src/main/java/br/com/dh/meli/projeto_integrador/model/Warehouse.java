package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Warehouse Entity
 *
 * @author Evelyn Cristini Oliveira
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private Long id;
    @NotNull
    private String warehouseCode;
    @NotEmpty(message = "A classe Address não pode estar vazio.")
    private Address address;
    @NotEmpty(message = "A lista de Representant deve ter pelo menos 1 válido.")
    public List<@Valid Representant> representant;
    @NotEmpty(message = "A lista de Seller deve ter pelo menos 1 representante válido.")
    private List<@Valid Seller> seller;
    @NotEmpty(message = "A lista de Section deve ter pelo menos 1 válido.")
    private List<@Valid Section> section;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Representant> getRepresentant() {
        return representant;
    }

    public void setRepresentant(List<Representant> representant) {
        this.representant = representant;
    }

    public List<Seller> getSeller() {
        return seller;
    }

    public void setSeller(List<Seller> seller) {
        this.seller = seller;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }
}
