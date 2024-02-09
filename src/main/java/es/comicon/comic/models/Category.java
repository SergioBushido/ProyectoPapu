package es.pixar.comicom.models;
//Estas librerias importan el contructor y los getter y setters
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//Estas anotaciones agregan las libreiras de lombok
@Entity //para que lo combierta en tabla
@AllArgsConstructor//contructor con todos los argumentos
@NoArgsConstructor//sin argumentos
@Data//getter y setters tostring equals..
@Table(name = "category")//nombre en minuscula para que no pille el de la clase

public class Category {
    @Id//esto indica que es la clave primaria
    @GeneratedValue//id autoincremental
    private int id;
    @NonNull
    private String name;


}
