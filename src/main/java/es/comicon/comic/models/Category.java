package es.comicon.comic.models;
//Estas librerias importan el contructor y los getter y setters
import jakarta.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)//id autoincremental
    private int id;
    @NonNull
    private String name;


}
