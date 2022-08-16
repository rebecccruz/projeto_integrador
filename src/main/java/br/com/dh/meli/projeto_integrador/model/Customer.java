package br.com.dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Person{
    @OneToOne
    @JoinColumn(name = "shoppingcart_id", nullable = true)
    private ShoppingCart shoppingCart;
    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<Order> historicShopping;
}
