package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.geolocalization.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateRepo extends JpaRepository<StateModel, Long> {
}
