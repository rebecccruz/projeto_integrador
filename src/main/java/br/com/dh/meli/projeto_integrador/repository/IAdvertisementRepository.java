package br.com.dh.meli.projeto_integrador.repository;

import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface IAdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a, b,s FROM Advertisement a JOIN  BatchStock b ON a.productId = b.productId" +
            "  JOIN Section s ON b.batchNumber= s.id WHERE s.category=?1")
    List<Advertisement> getAdvertisementByCategory(Optional<Category> category);
}
