package es.comicon.comic.models;
//Estas librerias importan el contructor y los getter y setters
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//Estas anotaciones agregan las libreiras de lombok


@Entity //para que lo combierta en tabla(esto define a una clase de entidad->representa a una tabla)
@AllArgsConstructor//contructor con todos los argumentos
@NoArgsConstructor//sin argumentos
@Data//anotacion de lomboc que contiene getter y setters tostring equals..
@Table(name = "category")//nombre en minuscula para que no pille el de la clase

public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //id autoincremental
    private int id;
    @NonNull
    private String name;


}
