package es.comicon.comic.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    @NonNull
    private String name;
    private String descripcion;
    @NonNull
    private Double precio;
    private boolean oferta;

}
