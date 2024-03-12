package es.comicon.comic.repositories;

import es.comicon.comic.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void should_find_product_by_id() {
        // Given
        final int id = 1;
        Product product = new Product();
        product.setId(id); // Suponiendo que existe un método setId en tu clase Product
        product.setName("Comic Book"); // Suponiendo que existe un método setName en tu clase Product
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When
        Optional<Product> result = productRepository.findById(id);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(id);
        assertThat(result.get().getName()).isEqualTo("Comic Book");
    }

    @Test
    void should_return_empty_optional_if_product_not_exist() {
        // Given
        final int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<Product> result = productRepository.findById(id);

        // Then
        assertThat(result).isEmpty();
    }

}
