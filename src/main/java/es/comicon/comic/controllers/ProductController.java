package es.comicon.comic.controllers;


import es.comicon.comic.models.Product;
import es.comicon.comic.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
//al controlador se le inyecta el servicio (Productservice que lleva la notacion service
    //los controladores actuan directamente sobre los servicios y los servicios sobre los repositorios
private final ProductService productService;
/*
    //Mostrar por id ...esta mierda no va en postman
    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable int id) throws Exception {
    return productService.getProductById(id);
    }*/

    //Mostrar por id
@GetMapping("/product/{id}")
public ResponseEntity<Product> getProduct(@PathVariable int id) {
    return productService.getProductById(id);
}


    //get
    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    //delete
    @DeleteMapping("/product/{id}")
    @ResponseBody
    public void deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
    }

    //insert
    @PostMapping("/product")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    //Actualizar
    @PutMapping("/product/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) throws Exception {
        return productService.updateProduct(id, product);
    }


}