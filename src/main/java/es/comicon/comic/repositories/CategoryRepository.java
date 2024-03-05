package es.comicon.comic.repositories;
//El repositorio es una capa intermedia entre el controlador y el modelo
import es.comicon.comic.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository//Para convertilo en repositorio
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
//<Category, Integer>  hay que indicar esto porque Spring Data JPA quiere un repositorio para la entidad Category con un tipo de identificador Integer (tal como esta definido en su clase),
// y este repositorio proporcionará automáticamente los métodos CRUD que alicará a las anotaciones @id @data que encontrara en la clase
// (donde estará esta interface inyectada)