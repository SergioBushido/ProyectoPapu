package es.comicon.comic.controllers;


import es.comicon.comic.models.Category;
import es.comicon.comic.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller//le indicamos al Frame Work que esto es un controlador
public class CategoryController {

    private CategoryRepository categoryRepository;
    @GetMapping("/category/{id}") // Asumiendo que la ruta del endpoint es "/category/{id}"
    public Category getCategory(@PathVariable int id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found with id: " + id));
    }

}
