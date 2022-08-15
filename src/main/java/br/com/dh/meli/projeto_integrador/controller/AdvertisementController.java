package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class AdvertisementController {

    @Autowired
    private IAdvertisementService service;


    /**
     * Veja uma lista de produtos por
     * categoria.
     * Se a lista não existir, ela deve retornar
     * um "404 Not Found".
     * @author Larissa Navarro
     * @return List<AdvertisementDTO>
     */
    @GetMapping("/list")
    public ResponseEntity<List<Advertisement>> getAllAdvertisementsByCategory(
            @RequestParam(required = false) Optional<String> category) {
        if(category.isPresent()) {
            Optional<Category> categoryBy = Optional.of(Category.getEnumName(category.get()));
            return ResponseEntity.ok(service.getAllAdvertisementByCategory(categoryBy));
        }
        return ResponseEntity.ok(service.getAllAdvertisement());
    }

    /**
     * Create Advertisement
     *
     * @param advertisement
     * @return ResponseEntity<AdvertisementDTO>
     */
    @PostMapping
    public ResponseEntity<AdvertisementDTO> createAdvertisement(@RequestBody AdvertisementDTO advertisement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAdvertisement(advertisement));
    }
}
