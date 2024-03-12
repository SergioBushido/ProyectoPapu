package es.comicon.comic.services;

import es.comicon.comic.models.Product;
import es.comicon.comic.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductByIdFound() {
        // Given
        final int id = 1;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<Product> response = productService.getProductById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void getProductByIdNotFound() {
        // Given
        final int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Product> response = productService.getProductById(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getProductsSuccess() {
        // Given
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> result = productService.getProducts();

        // Then
        assertEquals(productList.size(), result.size());
    }

    @Test
    void saveProductSuccess() {
        // Given
        Product product = new Product();
        product.setName("New Product");
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        Product result = productService.saveProduct(product);

        // Then
        assertEquals(product.getName(), result.getName());
    }

    @Test//Hay que meter todas las propiedades del producto??la del precio hay que meterla si o si por el not null
    void updateProductSuccess() throws Exception {
        // Given
        final int id = 1;
        Product existingProduct = new Product();
        existingProduct.setId(id);
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Name");
        updatedProduct.setPrecio(100.20);
        updatedProduct.getDescripcion();
        updatedProduct.getStock();
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // When
        Product result = productService.updateProduct(id, updatedProduct);

        // Then
        assertEquals(updatedProduct.getName(), result.getName());
    }

    @Test
    void updateProductNotFound() {
        // Given
        final int id = 1;
        Product updatedProduct = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> productService.updateProduct(id, updatedProduct));
        assertEquals("Producto not found with id: " + id, exception.getMessage());
    }

    @Test
    void deleteProductByIdSuccess() {
        // Given
        final int id = 1;

        // When
        productService.deleteProductById(id);

        // Then
        verify(productRepository).deleteById(id);
    }
}
