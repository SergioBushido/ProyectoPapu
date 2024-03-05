package es.comicon.comic.services;

import es.comicon.comic.models.Category;
import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service //esta anotacion hace que sea un servicio o un bean que springboot lo escanea automaticamente
@AllArgsConstructor//notacion lombok
public class CategoryService {

    private CategoryRepository categoryRepository;//inyectamos como atributo la interface categoryRepository que extiende el crudrepository


    //Mostrar por id
    public CategoryDto getCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(category -> CategoryDto.builder().name(category.getName()).build())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }


    //Metodo para Mostrar
    //List<Category> es un tipo (como lo puede ser int o boolean) y tiene que coincidir con lo que nos devuelve findAll,que es un tipo "iterable"
    //en este caso queremos trabajar con list ...asi que por eso hacemos un casteo
    @Transactional
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> CategoryDto.builder().name(category.getName()).build())
                .collect(Collectors.toList());
    }


    //Metodo para insertar
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return categoryDto;
    }

    //Metodo para actualizar
    public CategoryDto updateCategory(int id, CategoryDto categoryDto) throws Exception {
        // Comprobar si la categoría con el ID dado existe
        if (!categoryRepository.existsById(id)) {
            throw new Exception("Category not found with id: " + id);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return categoryDto;
    }


    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

}
