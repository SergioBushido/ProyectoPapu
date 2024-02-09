package es.pixar.comicom.repositories;

import es.pixar.comicom.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository                                             //recibe los parametros de la clase y el tipo de la clave primaria
public interface ProductRepository extends CrudRepository <Product,Integer>{
}
