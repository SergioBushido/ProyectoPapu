package es.comicon.comic.services;

import es.comicon.comic.models.Product;
import es.comicon.comic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;//metemos como atributo la clase que ademas tiene la interface

    //mostrar por id
    public Product getProductById(@PathVariable int id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));
    }


    //para mostrarlo todo
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    //para eliminar

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    //para insertar
    // Método para guardar o actualizar un producto
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}
