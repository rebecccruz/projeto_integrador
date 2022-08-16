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
 * Geolocalization/Zip Code Entity
 *
 * @author Evelyn Cristini Oliveira / Alexandre Borges Souza
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zipcode")
public class ZipCodeModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(nullable = false, unique = true)
    private String zip_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private CityModel city;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zip")
    private List<AddressModel> address;

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
