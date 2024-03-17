package es.comicon.comic.controllers;


import es.comicon.comic.models.dto.ProductDto;
import es.comicon.comic.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProductController", description = "Controlador para operaciones relacionadas con productos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
//al controlador se le inyecta el servicio (Productservice que lleva la notacion service
    //los controladores actuan directamente sobre los servicios y los servicios sobre los repositorios
private final ProductService productService;

    // Mostrar por id
    @Operation(summary = "Obtiene un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Product no encontrado")
    })
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_READ')")
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@Parameter(description = "ID de la producto a obtener", required = true)
                                       @PathVariable int id) throws Exception {
        return productService.getProductById(id);
    }

    // Listar todos
    @Operation(summary = "Obtiene todas los productos disponibles")
    @GetMapping("/products")
    //@ResponseBody si utilizao restcontroller no hace falta solo se usa con controller
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    // Borrar
    @Operation(summary = "Elimina una producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{id}")
   // @ResponseBody
    public void deleteProductById(@Parameter(description = "ID de la Producto a eliminar", required = true)
            @PathVariable int id) {
        productService.deleteProductById(id);
    }

    // Insertar
    @Operation(summary = "Añade un nuevo Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "404", description = "El Producto no se ha podido crear")
    })
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_CREATE')")
    @PostMapping("/product")
    public ProductDto addProduct(@Parameter(description = "Objeto producto que será añadido", required = true)
            @RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }


    // Actualizar
    @Operation(summary = "Actualiza un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "El Producto no se ha podido actualizar")
    })
    @PreAuthorize("hasRole('ROLE_REGISTER_USER') and hasAuthority('REGISTER_USER_UPDATE')")
    @PutMapping("/product/{id}")
    public ProductDto updateProduct(@PathVariable int id, @Parameter(description = "Objeto producto que será actualizado", required = true)
                                    @RequestBody ProductDto productDto) throws Exception {
        return productService.updateProduct(id, productDto);
    }


}