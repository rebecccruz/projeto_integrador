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
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    ISellerRepository repo;

    @Autowired
    SellerService service;

    @GetMapping("/all")
    public ResponseEntity<List<Seller>> getAllSellers(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id){
        return ResponseEntity.ok(service.findSellerById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@Valid @RequestBody Seller seller){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveSeller(seller));
    }

    @PutMapping("/update")
    public Seller updateSeller(@Valid @RequestBody Seller seller){
        return service.updateSeller(seller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id){
        service.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
