package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.model.geolocalization.AddressModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("person")
//    private AddressModel address;
}
