package es.comicon.comic.controllers;

import es.comicon.comic.models.Category;
import es.comicon.comic.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "CategoryController", description = "Controlador para operaciones relacionadas con categorías")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Obtiene una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/category/{id}")
    public Category getCategoryById(@Parameter(description = "ID de la categoría a obtener", required = true)
                                    @PathVariable int id) throws Exception {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Obtiene todas las categorías disponibles")
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @Operation(summary = "Elimina una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/category/{id}")
    public void deleteById(@Parameter(description = "ID de la categoría a eliminar", required = true)
                           @PathVariable int id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "Añade una nueva categoría")
    @PostMapping("/category")
    public Category addCategory(@Parameter(description = "Objeto categoría que será añadido", required = true)
                                @RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @Operation(summary = "Actualiza una categoría existente")
    @PutMapping("/category")
    public Category updateCategory(@Parameter(description = "Objeto categoría que será actualizado", required = true)
                                   @RequestBody Category category) throws Exception {
        return categoryService.updateCategory(category);
    }
}
