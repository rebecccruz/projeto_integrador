package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.mapper.IShoppingCartMapper;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService service;

    /**
     * getShoppingCart
     * @param shoppingCartId
     * @author Larissa Navarro e Isaias Finger
     */

    @GetMapping("/shopping-cart/orders/{shoppingCartId}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable Long shoppingCartId){
        return ResponseEntity.ok(service.convertToDTO(service.getShoppingCartById(shoppingCartId)));
    }

    /**
     * create shoppingCart
     * @param shoppingCartDto
     * @author Larissa Navarro e Isaias Finger
     */
    @PostMapping("/shopping-cart")
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.convertToDTO(service.createShoppingCart(shoppingCartDto)));
    }

    /**
     * create shoppingCart
     * @param shoppingCartId
     * @param status
     * @author Larissa Navarro e Isaias Finger
     */
    @PutMapping("/shopping-cart/{shoppingCartId}")
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable @Valid Long shoppingCartId, @RequestParam(required = false) Optional<String> status){
        if(status.isEmpty()){
            throw new BadRequestException("Status is required");
        }
        Status statusOrder = Status.getEnumName(status.get());
        return new ResponseEntity<ShoppingCartDTO>(service.convertToDTO(service.updateShoppingCart(shoppingCartId,statusOrder)), HttpStatus.ACCEPTED);
    }
}
