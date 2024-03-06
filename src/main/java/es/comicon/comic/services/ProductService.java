package es.comicon.comic.services;

import es.comicon.comic.models.Category;
import es.comicon.comic.models.Product;
import es.comicon.comic.models.dto.CategoryDto;
import es.comicon.comic.models.dto.ProductDto;
import es.comicon.comic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;//inyectamos como atributo la interface que ademas tiene la extiende de crudrepository


    //mostrar por id con la correccion a dto
public ProductDto getProductById(int id) {
    return productRepository.findById(id)
            .map(Product -> ProductDto.builder().name(Product.getName()).build())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
}



    //para mostrarlo todo corregido a dto
    //como findall devuelve un iterable hay que castearlo a List
    @Transactional
    public List<ProductDto> getProducts() {
        return productRepository.findAllBy()
                .map(product -> ProductDto.builder().name(product.getName()).build())
                .collect(Collectors.toList());
    }

    //para eliminar

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    //para insertar corregido a dto
    // Método para guardar o actualizar un producto
    public ProductDto addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescripcion(productDto.getDescripcion());
        product.setPrecio(productDto.getPrecio());
        productDto.setOferta(productDto.isOferta());
        productRepository.save(product);
        return productDto;

    }


    //Metodo para actualizar corregido a dto
    public ProductDto updateProduct(int id, ProductDto productDto) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));

        // Actualizar las propiedades del producto
        product.setName(productDto.getName());
        product.setDescripcion(productDto.getDescripcion());
        product.setPrecio(productDto.getPrecio());
        product.setOferta(productDto.isOferta());


        productRepository.save(product);
        return productDto;

    }


}
