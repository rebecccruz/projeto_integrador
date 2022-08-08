package br.com.dh.meli.projeto_integrador.model.geolocalization;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Geolocalization/State Entity
 *
 * @author Alexandre Borges Souza
 */
@Data
@Entity
@Table(name = "state")
public class StateModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String timezone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryModel country;

    @Column
    private String initials;

    @Column(insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;

    @Column(insertable = true, updatable = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date updated_at;

    @Column(nullable = true)
    private Double rating;

    @PrePersist
    private void onCreate() {
        Date now = new Date();
        created_at = now;
        updated_at = now;
    }
}
