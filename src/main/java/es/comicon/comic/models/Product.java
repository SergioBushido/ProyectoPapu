package es.comicon.comic.models;
//Estas librerias importan el contructor y los getter y setters


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import java.util.Date;

//Estas anotaciones agregan las libreiras de lombok
@Entity //para que lo combierta en tabla
@AllArgsConstructor//contructor con todos los argumentos
@NoArgsConstructor//sin argumentos
@Data// esto es de lomboc getter y setters tostring equals..
@Table(name = "product")//nombre en minuscula para que no pille el de la clase

public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//id autoincremental
    private int id;
    @NonNull
    private String name;


    //private categoria_id;
    //@Column(unique = true) cuando necesitas un valor unico en la tabla
    private String descripcion;
    @NonNull
    private Double price;
    @NonNull
    private int stock;
    private boolean offer;
    private Date date;
   // private imagen;


}
