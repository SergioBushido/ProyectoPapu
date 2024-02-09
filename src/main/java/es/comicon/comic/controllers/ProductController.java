package es.comicon.comic.controllers;


import es.comicon.comic.models.Product;
import es.comicon.comic.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private ProductRepository productRepository;//metemos como atributo la clase que ademas tiene la interface

@GetMapping("product/{id}")
public Product getProduct(@PathVariable int id) throws Exception {
    return productRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));
}

}