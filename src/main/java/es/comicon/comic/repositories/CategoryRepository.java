package es.comicon.comic.repositories;
//El repositorio es una capa intermedia entre el controlador y el modelo
import es.comicon.comic.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository//Para convertilo en repositorio
public interface  CategoryRepository extends CrudRepository <Category, Integer> {
}
//Spring Data JPA quiere un repositorio para la entidad Category con un tipo de identificador Integer (tal como esta definido en su clase),
// y este repositorio proporcionará automáticamente los métodos CRUD que alicará a las anotaciones @id @data que encontrara en la clase
// (donde estará esta interface inyectada)