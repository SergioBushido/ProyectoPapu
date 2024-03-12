package es.comicon.comic.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private  int id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Double price;
    private boolean offer;

}
