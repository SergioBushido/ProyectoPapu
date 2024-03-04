package es.comicon.comic.services;

import es.comicon.comic.models.Category;
import es.comicon.comic.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service //esta anotacion hace que sea un servicio o un bean que springboot lo escanea automaticamente
@AllArgsConstructor//notacion lombok
public class CategoryService {

    private CategoryRepository categoryRepository;//inyectamos como atributo la interface categoryRepository que extiende el crudrepository



    //Mostrar por id
    public Category getCategoryById(int id) throws Exception {
        //la ia dice que pathvariable se utiliza en el controlador
       // public Category getCategoryById(@PathVariable int id) throws Exception {

            return categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found with id: " + id));
    }

    //Metodo para Mostrar
    //List<Category> es un tipo (como lo puede ser int o boolean) y tiene que coincidir con lo que nos devuelve findAll,que es un tipo "iterable"
    //en este caso queremos trabajar con list ...asi que por eso hacemos un casteo
    public List<Category> getCategories()  {
        return (List<Category>) categoryRepository.findAll();
    }

    //Metodo para insertar
   // public void setCategory(@RequestParam String name){
   //     categoryRepository.save(name);
   // }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Metodo para actualizar
    public Category updateCategory(Category category) throws Exception {
        // Comprobar si la categoría con el ID dado existe
        if (category.getId() <= 0 || !categoryRepository.existsById(category.getId())) {
            throw new Exception("Category not found with id: " + category.getId());
        }
        return categoryRepository.save(category);
    }



    public void deleteById(int id){
       // public void deleteById(@RequestParam int id){

            categoryRepository.deleteById(id);
    }

}
