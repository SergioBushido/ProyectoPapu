package es.comicon.comic.controllers;


import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Obtiene una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_READ')")
    @GetMapping("/category/{id}")
    public CategoryDto getCategoryById(@Parameter(description = "ID de la categoría a obtener", required = true)
                                    @PathVariable int id) throws Exception {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Obtiene todas las categorías disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = CategoryDto.class)))
    })
    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    @Operation(summary = "Elimina una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/category/{id}")
    public void deleteById(@Parameter(description = "ID de la categoría a eliminar", required = true)
                           @PathVariable int id) {
        categoryService.deleteById(id);
    }


    @Operation(summary = "Añade una nueva categoría")
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_CREATE')")
    @PostMapping("/category")
    public CategoryDto addCategory(@Parameter(description = "Objeto categoría que será añadido", required = true)
                                @RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @Operation(summary = "Actualiza una categoría existente")
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_UPDATE')")
    @PutMapping("/category/{id}")
    public CategoryDto updateCategory(@PathVariable int id, @Parameter(description = "Objeto categoría que será actualizado", required = true)
                                   @RequestBody CategoryDto categoryDto) throws Exception {
        return categoryService.updateCategory(id, categoryDto);
    }
}
