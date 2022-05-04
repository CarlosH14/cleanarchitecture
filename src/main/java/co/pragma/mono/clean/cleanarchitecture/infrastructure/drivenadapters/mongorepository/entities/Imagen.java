package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities;

import javax.persistence.*;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
	private Integer id ;
    @Field
	private Binary photo;

}
