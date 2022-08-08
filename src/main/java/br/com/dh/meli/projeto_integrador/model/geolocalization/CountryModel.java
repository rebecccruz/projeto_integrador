package br.com.dh.meli.projeto_integrador.model.geolocalization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Geolocalization/Country Entity
 *
 * @author Alexandre Borges Souza
 */
@Data
@Entity
@Table(name = "country")
public class CountryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String initials;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    @JsonIgnoreProperties("country")
    private List<StateModel> states;

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
}
