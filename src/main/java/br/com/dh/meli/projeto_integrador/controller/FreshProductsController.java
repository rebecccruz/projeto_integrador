package br.com.dh.meli.projeto_integrador.controller;

import br.com.dh.meli.projeto_integrador.dto.ProductDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.model.Product;
import br.com.dh.meli.projeto_integrador.service.IFreshProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class FreshProductsController {

    @Autowired
    private IFreshProductsService service;

    /**
     * Veja uma lista completa de produtos.
     * Se a lista não existir, ela deve retornar
     * um "404 Not Found".
     * @author Larissa Navarro
     * @return List<ProductDTO>
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    /**
     *Veja uma lista de produtos por
     * categoria.
     * Se a lista não existir, ela deve retornar
     * um "404 Not Found".
     * @author Larissa Navarro
     * @return List<ProductDTO>
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategory(@RequestParam(required = false) Optional<String> category){
        Optional<Category> categoryBy = Optional.empty();
        if(category.isPresent()){
            categoryBy = Optional.of(Category.valueOf(category.get()));
        }
        return ResponseEntity.ok(service.getAllProductsByCategory(categoryBy));
    }

    /**
     * Registre um pedido com a lista de
     * produtos que compõem o PurchaseOrder.
     * @author Larissa Navarro
     * @return ProductDTO
     */
    @PostMapping("/orders/")
    public ResponseEntity<ProductDTO> createPurchaseOrder(@RequestBody Product product){
        ProductDTO dto = new ProductDTO(product);
        return new ResponseEntity<ProductDTO>(service.createProduct(product), HttpStatus.CREATED);
    }

    /**
     * Mostrar produtos no pedido.
     * @author Larissa Navarro
     * @return List<ProductDTO>
     */
    @GetMapping("/orders/")
    public ResponseEntity<List<ProductDTO>> getAllProductsByOrder(@RequestParam String idOrder){
        return ResponseEntity.ok((service.getAllProductsByOrder(idOrder)));
    }
    
    /**
     * Modifique o pedido existente para
     * torná-lo do tipo de carrinho
     * ABERTO/FINALIZADO
     * @author Larissa Navarro
     * @return ProductDTO
     */
    @PutMapping
    public ResponseEntity<ProductDTO> updatePurchaseOrder(@RequestBody Product product){
        ProductDTO dto = new ProductDTO(product);
        return new ResponseEntity<ProductDTO>(service.updateProduct(product),HttpStatus.ACCEPTED);
    }
}