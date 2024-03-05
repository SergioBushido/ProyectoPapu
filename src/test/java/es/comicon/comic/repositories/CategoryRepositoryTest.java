package es.comicon.comic.repositories;

import es.comicon.comic.models.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

        @Mock
        private CategoryRepository categoryRepository;

        @Test
        void should_find_by_name() {
            // Given
            final int id = 1;
            Category category = new Category(id, "Vinagre");
            when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

            // When
            final var result = categoryRepository.findById(id);

            // Then
            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(id);
        }

        @Test
        void should_return_empty_optional_if_not_exist() {
            // Given
            final int id = 1;
            when(categoryRepository.findById(id)).thenReturn(Optional.empty());

            // When
            final var result = categoryRepository.findById(id);

            // Then
            assertThat(result).isEmpty();
        }
}
