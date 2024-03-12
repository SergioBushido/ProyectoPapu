package es.comicon.comic.controllers;

import es.comicon.comic.models.Product;
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
    void should_get_product_by_id() {
        // Given
        final int id = 1;
        Product product = new Product(); //dentro de ProductService esta el constructor y aqui lo podemos usar gracias a @Mock
        product.setId(id);
        product.setName("Comic Book");
        // Simula la respuesta esperada del servicio cuando se busca un producto por su ID
        when(productService.getProductById(id)).thenReturn(ResponseEntity.ok(product));

        // When
        // Invoca el método del controlador para obtener un producto por ID
        ResponseEntity<Product> response = productController.getProduct(id);

        // Then
        // Verifica que el servicio fue llamado correctamente y que la respuesta tiene el estado y el cuerpo esperados
        verify(productService).getProductById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void should_get_all_products() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        // Simula la respuesta esperada del servicio cuando se solicita la lista de todos los productos
        when(productService.getProducts()).thenReturn(productList);

        // When
        // Invoca el método del controlador para obtener todos los productos
        List<Product> result = productController.getProducts();

        // Then
        // Verifica que el servicio fue llamado correctamente y que la lista de productos es la esperada
        verify(productService).getProducts();
        assertEquals(productList, result);
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
        Product product = new Product();
        // Simula la respuesta esperada del servicio cuando se agrega un nuevo producto
        when(productService.saveProduct(product)).thenReturn(product);

        // When
        // Invoca el método del controlador para añadir un nuevo producto
        Product result = productController.addProduct(product);

        // Then
        // Verifica que el servicio fue llamado correctamente con el producto a añadir y que el producto retornado es el esperado
        verify(productService).saveProduct(product);
        assertEquals(product, result);
    }

    @Test
    void should_update_product() throws Exception {
        // Given
        final int id = 1;
        Product product = new Product();
        // Simula la respuesta esperada del servicio cuando se actualiza un producto
        when(productService.updateProduct(id, product)).thenReturn(product);

        // When
        // Invoca el método del controlador para actualizar un producto existente
        Product result = productController.updateProduct(id, product);

        // Then
        // Verifica que el servicio fue llamado con el ID y el producto correctos para la actualización, y que el producto retornado es el esperado
        verify(productService).updateProduct(id, product);
        assertEquals(product, result);
    }

}
