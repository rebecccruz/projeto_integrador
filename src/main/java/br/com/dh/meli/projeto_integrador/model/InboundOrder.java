package br.com.dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.*;
import java.time.*;
import java.util.*;

@Data
@Entity
public class InboundOrder {
    @Id
    @Column(name = "id", nullable = false)
    public Integer orderNumber;

    @Column(name = "order_date", nullable = false)
    public LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    public Section sectionCode;

    @OneToMany(mappedBy = "inboundOrder", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("inboundOrder")
    private List<BatchStock> batchStocks;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    private Warehouse warehouse;
}
