package es.comicon.comic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import es.comicon.comic.models.Category;
import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock // mock de la clase CategoryRepository
    private CategoryRepository categoryRepository;

    @InjectMocks // crea una instancia de la clase y inyecta los mocks creados con @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        // Inicialización de objectos si fuese necesario
    }

    @Test
    void getCategoryByIdSuccess() {
        // Given
        int categoryId = 1;
        Category mockCategory = new Category();
        mockCategory.setName("Comedia");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        // When
        CategoryDto result = categoryService.getCategoryById(categoryId);

        // Then
        assertEquals("Comedia", result.getName());
    }

    @Test
    void getCategoryByIdNotFound() {
        // Given
        int categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });
    }

    @Test
    void getCategoriesSuccess() {
        // Given
        Category category1 = new Category();
        category1.setName("Comedia");
        Category category2 = new Category();
        category2.setName("Acción");
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAllBy()).thenReturn(categories.stream());

        // When
        List<CategoryDto> result = categoryService.getCategories();

        // Then
        assertEquals(2, result.size());
        assertEquals("Comedia", result.get(0).getName());
        assertEquals("Acción", result.get(1).getName());
    }

    @Test
    void addCategorySuccess() {
        // Given
        CategoryDto newCategoryDto = new CategoryDto();
        newCategoryDto.setName("Fantasía");

        Category newCategory = new Category();
        newCategory.setName("Fantasía");

        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        // When
        CategoryDto result = categoryService.addCategory(newCategoryDto);

        // Then
        assertEquals("Fantasía", result.getName());
    }

    @Test
    void updateCategorySuccess() throws Exception {
        // Given
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Fantasía");

        Category category = new Category();
        category.setName("Fantasía");

        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // When
        CategoryDto result = categoryService.updateCategory(categoryId, categoryDto);

        // Then
        assertEquals("Fantasía", result.getName());
    }

    @Test
    void updateCategoryNotFound() {
        // Given
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Fantasía");

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        // When and Then
        assertThrows(Exception.class, () -> {
            categoryService.updateCategory(categoryId, categoryDto);
        });
    }

    @Test
    void deleteByIdSuccess() {
        // Given
        int categoryId = 1;

        // When
        categoryService.deleteById(categoryId);

        // Then
        verify(categoryRepository).deleteById(categoryId);
    }

    @Test
    void deleteByIdNotFound() {
        // Given
        int categoryId = 1;

        // When
        doThrow(new ResourceNotFoundException("Category not found with id: " + categoryId))
            .when(categoryRepository).deleteById(categoryId);

        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.deleteById(categoryId);
        });
    }
}
