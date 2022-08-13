package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_item")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderNumber;
    private LocalDateTime orderDate;
    private Status orderStatus;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("order")
    private List<Item> items;
    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    @JsonIgnoreProperties("order")
    private Customer costumer;
}
