package br.com.dh.meli.projeto_integrador.model.geolocalization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Geolocalization/City Entity
 *
 * @author Evelyn Cristini Oliveira / Alexandre Borges Souza
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class CityModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private StateModel state;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city")
    private List<ZipCodeModel> zips;

    @Column(insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;

    @Column(insertable = true, updatable = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date updated_at;

    @PrePersist
    private void onCreate() {
        Date now = new Date();
        created_at = now;
        updated_at = now;
    }

    @PreUpdate
    private void onUpdate() {
        Date now = new Date();
        updated_at = now;
    }
}
