package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.ISellerRepository;
import br.com.dh.meli.projeto_integrador.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SellerController {

    @Autowired
    ISellerRepository repo;

    @Autowired
    SellerService service;

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id){
        return repo.findBySeller(id)
                .map(s -> ResponseEntity.ok(s))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Seller> registerSeller(@Valid @RequestBody Seller seller){
        return service.saveSeller(seller)
                .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(s))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping
    public ResponseEntity<Seller> updateSeller(@Valid @RequestBody Seller seller){
        return service.updateSeller(seller)
                .map(s -> ResponseEntity.status(HttpStatus.OK).body(s))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSeller(@PathVariable Long id){
        return repo.findBySeller(id)
                .map(s -> {
                    repo.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
