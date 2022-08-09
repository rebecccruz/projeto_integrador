package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.service.IProductService;
import br.com.dh.meli.projeto_integrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private IProductService service;


    /**
     * Veja uma lista de produtos por
     * categoria.
     * Se a lista não existir, ela deve retornar
     * um "404 Not Found".
     * @author Larissa Navarro
     * @return List<ProductDTO>
     */
    @GetMapping("/products/list")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam(required = false) Optional<String> category){
        Optional<Category> categoryBy = Optional.empty();
        if(category.isPresent()){
            categoryBy = Optional.of(Category.getEnumName(category.get()));
            return ResponseEntity.ok(service.getAllProductsByCategory(categoryBy));
        }
        return ResponseEntity.ok(service.getAllProducts());
    }

    /**
     * Registre um pedido com a lista de
     * produtos que compõem o PurchaseOrder.
     * @author Larissa Navarro
     * @return ProductDTO
     */
    @PostMapping("/orders/")
    public ResponseEntity<Product> createPurchaseOrder(@RequestBody Product product){
        return new ResponseEntity<Product>(service.createProduct(product), HttpStatus.CREATED);
    }
}
