package es.comicon.comic.services;

import es.comicon.comic.models.Category;
import es.comicon.comic.models.Product;
import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.models.dto.ProductDto;
import es.comicon.comic.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
        Product mockProduct = new Product();
        mockProduct.setName("Comedia");
        mockProduct.setPrecio(11.1);
        when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));

        // When
        ProductDto result = productService.getProductById(id);

        // Then
        assertEquals("Comedia", result.getName());
    }

    @Test
    void getProductByIdNotFound() {
        // Given
        final int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> {
            // This is where the exception is expected to be thrown
            productService.getProductById(id);
        });
    }


    @Test
    void getProductsSuccess() {
        Product product1 = new Product();
        product1.setName("loquesea");
        product1.setPrecio(20.2);
        Product product2 = new Product();
        product2.setName("mierdicion");
        product2.setPrecio(99.1);
        List<Product> products= Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // When
        List<ProductDto> result = productService.getProducts();

        // Then
        assertEquals(2, result.size());
        assertEquals("loquesea", result.get(0).getName());
        assertEquals("mierdicion", result.get(1).getName());
    }

    @Test
    void saveProductSuccess() {
        // Given
        ProductDto newProductDto = new ProductDto();
        newProductDto.setName("loquesea");
        newProductDto.setPrice(66.4);

        Product newProduct= new Product();
        newProduct.setName("loquesea");

        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        // When
        ProductDto result = productService.addProduct(newProductDto);

        // Then
        assertEquals("loquesea", result.getName());
    }
    @Test
    void updateProductSuccess() throws Exception {
        // Given

        //hay que crear 2 objetos el segundo lo creamos con el builder y referencia al primero
        //se pueden poner los datos dentro del builder o se pueden utilizar los getters
        final int id = 1;
        Product product = new Product();
        product.setPrecio(100.3);
        product.setName("mierdas");
        product.setDescripcion("mierda");
        product.setOferta(true);

        final var updatedProduct = ProductDto.builder()
                .id(id)
                //builder es un metodo statico para hacer instancias de clase
                //(que es lo mismo que hacer un new ProductDto
                .price(100.3)
                .name("mierdas")
                .description("mierda")
                .offer(true)
                .build();
        //se empieza con builder y se acaba con build


        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        ProductDto result = productService.updateProduct(id, updatedProduct);

        // Then
        assertEquals(updatedProduct.getName(), result.getName());
    }

    @Test
    void updateProductNotFound() {
        // Given
        final int id = 1;
        //ProductDto updatedProduct = new ProductDto();
        //esto es lo mismo que lo de arriba
        final var updatedProductDto = ProductDto.builder()
                .name("joder")
                .price(222.2)
                .build();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> productService.updateProduct(id, updatedProductDto));
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
