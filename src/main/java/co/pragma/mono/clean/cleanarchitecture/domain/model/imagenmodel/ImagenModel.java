package co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel;

import org.bson.types.Binary;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ImagenModel {

    private Integer id;
    private Binary photo;

}