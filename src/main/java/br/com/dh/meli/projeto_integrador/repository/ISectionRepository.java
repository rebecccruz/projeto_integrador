package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISectionRepository extends JpaRepository<Section, Long> {
}
