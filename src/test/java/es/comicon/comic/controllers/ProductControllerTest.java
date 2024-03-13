package es.comicon.comic.controllers;

import es.comicon.comic.models.Product;
import es.comicon.comic.models.dto.ProductDto;
import es.comicon.comic.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock //con esto vamos a falsear el servicio y se inyecta en las instancias de inyectmoks
    private ProductService productService;

    @InjectMocks//crea instancias de una clase y le inyectamos los @Moks
    private ProductController productController;

    @Test
    void should_get_product_by_id() throws Exception {
        // Given
        final int id = 1;
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName("Comic Book");

        when(productService.getProductById(id)).thenReturn(productDto);

        // When
       ProductDto result = productController.getProductById(id);

        // Then
        verify(productService).getProductById(id);
        assertEquals(productDto, result);
    }


    @Test
    void should_get_all_products() {
        // Given
        //When
        productController.getProducts();
        //Then
        verify(productService).getProducts();
    }

    @Test
    void should_delete_product_by_id() {
        // Given
        final int id = 1;

        // When
        // Invoca el método del controlador para eliminar un producto por su ID
        productController.deleteProductById(id);

        // Then
        // Verifica que el servicio fue llamado con el ID correcto para eliminar el producto
        verify(productService).deleteProductById(id);
    }

    @Test
    void should_add_product() {
        // Given
        ProductDto productDto = new ProductDto();
        when(productService.addProduct(productDto)).thenReturn(productDto);

        // When
        ProductDto result = productController.addProduct(productDto);

        // Then
        verify(productService).addProduct(productDto);
        assertEquals(productDto, result);
    }

    @Test
    void should_update_product() throws Exception {
        // Given
        final int id = 1;
        ProductDto productDto = new ProductDto();
        when(productService.updateProduct(id, productDto)).thenReturn(productDto);

        // When
        ProductDto result = productController.updateProduct(id, productDto);

        // Then
        verify(productService).updateProduct(id, productDto);
        assertEquals(productDto, result);
    }

}
