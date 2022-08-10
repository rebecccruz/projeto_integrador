package br.com.dh.meli.projeto_integrador.controller;

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
@RequestMapping("/api/v1/fresh-Advertisements")
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
    @GetMapping("/Advertisements/list")
    public ResponseEntity<List<Advertisement>> getAllAdvertisementsByCategory(@RequestParam(required = false) Optional<String> category){
        Optional<Category> categoryBy = Optional.empty();
        if(category.isPresent()){
            categoryBy = Optional.of(Category.getEnumName(category.get()));
            return ResponseEntity.ok(service.getAllAdvertisementByCategory(categoryBy));
        }
        return ResponseEntity.ok(service.getAllAdvertisement());
    }

    /**
     * Registre um pedido com a lista de
     * produtos que compõem o PurchaseOrder.
     * @author Larissa Navarro
     * @return AdvertisementDTO
     */
    @PostMapping("/orders/")
    public ResponseEntity<Advertisement> createPurchaseOrder(@RequestBody Advertisement advertisement){
        return new ResponseEntity<Advertisement>(service.createAdvertisement(advertisement), HttpStatus.CREATED);
    }
}
