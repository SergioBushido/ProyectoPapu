package es.pixar.comicom.models;
//Estas librerias importan el contructor y los getter y setters
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

//Estas anotaciones agregan las libreiras de lombok
@Entity //para que lo combierta en tabla
@AllArgsConstructor//contructor con todos los argumentos
@NoArgsConstructor//sin argumentos
@Data//getter y setters tostring equals..
@Table(name = "product")//nombre en minuscula para que no pille el de la clase

public class Product {
    @Id
    @GeneratedValue//id autoincremental
    private int id;
    @NonNull
    private String name;


    //private categoria_id;
    //@Column(unique = true) cuando necesitas un valor unico en la tabla
    private String descripcion;
    @NonNull
    private Double precio;
    @NonNull
    private int stock;
    private boolean oferta;
    private Date fecha;
   // private imagen;


}
