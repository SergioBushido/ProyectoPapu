package es.comicon.comic.controllers;


import es.comicon.comic.models.Category;
import es.comicon.comic.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller//le indicamos al Frame Work que esto es un controlador
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;//inyectamos la calse categoryservice ( que tiene inyectada categoryrepository que esta a su vez extiende de crudrepository) con esta inyeccion se puede operar desde el controlador indirectamente en la base de datos


    @GetMapping("/category/{id}") // Asumiendo que la ruta del endpoint es "/category/{id}"
    @ResponseBody
    /* Esta anotación indica que la respuesta del método debe ser vinculada al cuerpo de la respuesta HTTP, convirtiendo el objeto Category
    (o la lista de objetos Category) en JSON u otro formato de respuesta adecuado, según la configuración de Spring MVC.*/
    public Category getCategoryById(@PathVariable int id) throws Exception {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategories(){
       return categoryService.getCategories();
    }

    @DeleteMapping("/category/{id}")
    @ResponseBody
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }


}
