package co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.gateways;
import java.util.*;


import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;

//@Repository
public interface PersonaModelRepository {

    List<PersonaModel> findAll();
    Optional<PersonaModel> findById(Integer id);
    PersonaModel save(PersonaModel personaModel);
    PersonaModel update(Integer id, PersonaModel personaModelUpdate) ;
    String deleteById(Integer id);

    List<PersonaModel> findByTypeid(String typeid);
    List<PersonaModel> findByNumberid(Long numberid);
    List<PersonaModel> findByTypeidAndNumberid(String typeid, Long numberid);
    List<PersonaModel> findByAgeGreaterThanEqual(Integer age);
    
}
