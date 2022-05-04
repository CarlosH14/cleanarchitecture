package co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonaModel {

    private Integer id;
    private String name;
    private String lastname;
    private String city;
    private Integer age;
    private String typeid;
    private Long numberid;
    private Integer imagen;

}