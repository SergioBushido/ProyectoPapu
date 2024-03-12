package es.comicon.comic.services;

import es.comicon.comic.models.Product;
import es.comicon.comic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;//inyectamos como atributo la interface que ademas tiene la extiende de crudrepository
/*
    //mostrar por id esta mierda no va
    public Product getProductById(@PathVariable int id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));
    }*/

    //mostrar por id
public ResponseEntity<Product> getProductById(int id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    } else {
        // Producto no encontrado
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



    //para mostrarlo todo
    //como findall devuelve un iterable hay que castearlo a List
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
    //recibe un parametro que es una instancia de la clase Product

    //Metodo para actualizar
    public Product updateProduct(int id, Product updatedProduct) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));

        // Actualizar las propiedades del producto
        product.setName(updatedProduct.getName());
        product.setDescripcion(updatedProduct.getDescripcion());
        product.setPrecio(updatedProduct.getPrecio());
        product.setStock(updatedProduct.getStock());
        product.setOferta(updatedProduct.isOferta());
        product.setFecha(updatedProduct.getFecha());


        return productRepository.save(product);
    }


}
