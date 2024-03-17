package es.comicon.comic.repositories;

import es.comicon.comic.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryIntegrationTest {

    @Container
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
            .withUsername("root")
            .withPassword("root")
            .withDatabaseName("comicon");

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenSaveProduct_thenFindById() {
        // Given
        Product product = new Product();
        product.setName("Coffee");
        product.setPrice(10.99);

        // When
        product = productRepository.save(product);
        Product foundProduct = productRepository.findById(product.getId()).orElse(null);

        // Then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct).isEqualTo(product);
        assertThat(foundProduct.getName()).isEqualTo("Coffee");
        assertThat(foundProduct.getPrice()).isEqualTo(10.99);
    }
}
