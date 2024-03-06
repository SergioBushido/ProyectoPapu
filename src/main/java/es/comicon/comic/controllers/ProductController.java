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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProductController", description = "Controlador para operaciones relacionadas con productos")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
//al controlador se le inyecta el servicio (Productservice que lleva la notacion service
    //los controladores actuan directamente sobre los servicios y los servicios sobre los repositorios
private final ProductService productService;
/*
    //Mostrar por id ...esta mierda no va en postman
    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable int id) throws Exception {
    return productService.getProductById(id);
    }*/

    //Mostrar por id actualizado a dto
    @Operation(summary = "Obtiene un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Product no encontrado")
    })
@GetMapping("/product/{id}")
public ProductDto getProductById(@Parameter(description = "ID de la producto a obtener", required = true)
                                   @PathVariable int id) throws Exception {
    return productService.getProductById(id);
}

    //get actualizao a dto
    @Operation(summary = "Obtiene todas los productos disponibles")
    @GetMapping("/products")
    //@ResponseBody si utilizao restcontroller no hace falta solo se usa con controller
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    //delete
    @Operation(summary = "Elimina una producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/product/{id}")
   // @ResponseBody
    public void deleteProductById(@Parameter(description = "ID de la Producto a eliminar", required = true)
            @PathVariable int id) {
        productService.deleteProductById(id);
    }

    //insert corregido a dto
    @Operation(summary = "Añade un nuevo Producto")
    @PostMapping("/product")

    public ProductDto addProduct(@Parameter(description = "Objeto producto que será añadido", required = true)
            @RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }


    //Actualizar
    @Operation(summary = "Actualiza un producto existente")
    @PutMapping("/product/{id}")
    //@ResponseBody
    public ProductDto updateProduct(@PathVariable int id, @Parameter(description = "Objeto producto que será actualizado", required = true)
                                    @RequestBody ProductDto productDto) throws Exception {
        return productService.updateProduct(id, productDto);
    }


}