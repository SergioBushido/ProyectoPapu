package es.comicon.comic.repositories;

import es.comicon.comic.models.Category;
import es.comicon.comic.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository                                             //recibe los parametros de la clase y el tipo de la clave primaria
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
