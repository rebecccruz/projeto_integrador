package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy="product")
    @JsonIgnoreProperties("product")
    private BatchStock  batchStock;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("product")
    private Seller seller;
    private Integer quantity;
    private double price;
    private String description;
}
