package es.comicon.comic.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder//permite hacer construcciones en el sitio donde se use del objeto
public class CategoryDto {
    private int id;
    @NonNull
    private String name;
}
