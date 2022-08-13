package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("section")
    private List<BatchStock> batchStocks;

    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("section")
    private List<InboundOrder> inboundOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonIgnoreProperties("sections")
    private Warehouse warehouse;
}
