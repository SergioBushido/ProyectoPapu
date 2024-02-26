package es.comicon.comic.controllers;


import es.comicon.comic.models.Product;
import es.comicon.comic.repositories.ProductRepository;
import es.comicon.comic.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
//al controlador se le inyecta el servicio (Productservice que lleva la notacion service
    //los controladores actuan directamente sobre los servicios y los servicios sobre los repositorios
private final ProductService productService;
@GetMapping("product/{id}")
public Product getProduct(@PathVariable int id) throws Exception {
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

}