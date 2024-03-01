package es.comicon.comic.controllers;


import es.comicon.comic.models.Category;
import es.comicon.comic.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller//le indicamos al Frame Work que esto es un controlador
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;//inyectamos la calse categoryservice ( que tiene inyectada categoryrepository que esta a su vez extiende de crudrepository) con esta inyeccion se puede operar desde el controlador indirectamente en la base de datos


    //Mostrar por id
    @GetMapping("/category/{id}") // Asumiendo que la ruta del endpoint es "/category/{id}"
    @ResponseBody
    /* Esta anotación indica que la respuesta del método debe ser vinculada al cuerpo de la respuesta HTTP, convirtiendo el objeto Category
    (o la lista de objetos Category) en JSON u otro formato de respuesta adecuado, según la configuración de Spring MVC.*/
    public Category getCategoryById(@PathVariable int id) throws Exception {
        return categoryService.getCategoryById(id);
    }

    //mostrar
    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategories(){
       return categoryService.getCategories();
    }

    //Eliminar
    @DeleteMapping("/category/{id}")
    @ResponseBody
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

    //Insertar
    @PostMapping("/category")
    @ResponseBody
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    //Actualizar
    @PutMapping("/category")
    @ResponseBody
    public Category updateCategory(@RequestBody Category category) throws Exception {
        return categoryService.updateCategory(category);
    }





}
