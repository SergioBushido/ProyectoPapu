package es.pixar.comicom.repositories;
//El repositorio es una capa intermedia entre el controlador y el modelo
import es.pixar.comicom.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository//Para convertilo en repositorio
public interface  CategoryRepository extends CrudRepository <Category, Integer> {
}
