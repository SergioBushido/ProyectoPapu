package es.comicon.comic.controllers;

import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

        @Mock
        private CategoryService categoryService;

        @InjectMocks
        private CategoryController categoryController;

        @Test
        void should_get_category_by_id() throws Exception {
            // Given
            final int id = 1;
            CategoryDto categoryDto = CategoryDto.builder().name("Vinagre").build();
            when(categoryService.getCategoryById(id)).thenReturn(categoryDto);

            // When
            CategoryDto result = categoryController.getCategoryById(id);

            // Then
            verify(categoryService).getCategoryById(id);
            assertEquals(categoryDto, result);
        }

        @Test
        void should_get_all_categories() {
            // Given
            // When
            categoryController.getCategories();
            // Then
            verify(categoryService).getCategories();
        }

        @Test
        void should_delete_category_by_id() {
            // Given
            final int id = 1;
            // When
            categoryController.deleteById(id);
            // Then
            verify(categoryService).deleteById(id);
        }

        @Test
        void should_add_category() {
            // Given
            CategoryDto categoryDto = CategoryDto.builder().name("Leches").build();
            when(categoryService.addCategory(categoryDto)).thenReturn(categoryDto);
            // When
            CategoryDto result = categoryController.addCategory(categoryDto);
            // Then
            verify(categoryService).addCategory(categoryDto);
            assertEquals(categoryDto, result);
        }

        @Test
        void should_update_category() throws Exception {
            // Given
            final int id = 1;
            CategoryDto categoryDto = CategoryDto.builder().name("Pollas").build();
            when(categoryService.updateCategory(id, categoryDto)).thenReturn(categoryDto);
            // When
            CategoryDto result = categoryController.updateCategory(id, categoryDto);
            // Then
            verify(categoryService).updateCategory(id, categoryDto);
            assertEquals(categoryDto, result);
        }


}
