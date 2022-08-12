package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService service;

    @PostMapping("/shopping-cart")
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.convertToDTO(service.createShoppingCart(shoppingCartDto)));
    }

    @PutMapping("/shopping-cart/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDto,@PathVariable @Valid Long shoppingCartId){
        return new ResponseEntity<ShoppingCart>(service.updateShoppingCart(shoppingCartDto,shoppingCartId), HttpStatus.ACCEPTED);
    }

}
