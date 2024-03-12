package es.comicon.comic.services;

import es.comicon.comic.models.Product;
import es.comicon.comic.models.dto.ProductDto;
import es.comicon.comic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;//inyectamos como atributo la interface que ademas tiene la extiende de crudrepository


    //mostrar por id con la correccion a dto
public ProductDto getProductById(int id) {
    return productRepository.findById(id)
            .map(product -> ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescripcion())
                    .offer(product.isOferta())
                    .price(product.getPrecio())
                    .build())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
}



    //para mostrarlo todo corregido a dto
    //como findall devuelve un iterable hay que castearlo a List
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescripcion())
                        .offer(product.isOferta())
                        .price(product.getPrecio())
                        .build())
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
        product.setDescripcion(productDto.getDescription());
        product.setPrecio(productDto.getPrice());
        productDto.setOffer(productDto.isOffer());
        productRepository.save(product);
        return productDto;

    }


    //Metodo para actualizar corregido a dto
    public ProductDto updateProduct(int id, ProductDto productDto) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto not found with id: " + id));

        // Actualizar las propiedades del producto
        product.setName(productDto.getName());
        product.setDescripcion(productDto.getDescription());
        product.setPrecio(productDto.getPrice());
        product.setOferta(productDto.isOffer());

        productRepository.save(product);
        return productDto;

    }


}
