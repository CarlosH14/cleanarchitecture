package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.pragma.mono.clean.cleanarchitecture.application.config.ObjectMapperConfig;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.gateways.PersonaModelRepository;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.MongoImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JPARepositoryAdapter implements PersonaModelRepository{

    @Autowired
    JPARepository jpaRepository;
    @Autowired
    MongoImageRepository mongoImageRepository;
    
    
    private ObjectMapperConfig objectMapperConfig = Mappers.getMapper(ObjectMapperConfig.class);

    @Override
    public List<PersonaModel> findAll() {
        List<Persona> persona = jpaRepository.findAll();
        List<PersonaModel> personaModel = new ArrayList<PersonaModel>();
        for (Persona p : persona) {
            personaModel.add(objectMapperConfig.PersonaToPersonaModel(p));
        }
        return personaModel;
    }

    @Override
    public Optional<PersonaModel> findById(Integer id) {
        Optional<Persona> persona = jpaRepository.findById(id);
        if(persona.isPresent()){
            return Optional.of(objectMapperConfig.PersonaToPersonaModel(persona.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public PersonaModel save(PersonaModel personaModel) {
        Persona persona;
        persona = objectMapperConfig.PersonaModelToPersona(personaModel);
        persona = jpaRepository.save(persona);
        PersonaModel result;
        result = objectMapperConfig.PersonaToPersonaModel(persona);
        return result;
    }

    @Override
    public PersonaModel update(Integer id, PersonaModel personaModelUpdate){
        Optional<Persona> persona = jpaRepository.findById(id);
        
        if (persona.isEmpty()) {
            return null;
        }

        if(personaModelUpdate.getName() == null || personaModelUpdate.getName().equals("")){
            personaModelUpdate.setName(persona.get().getName());
        }
        if(personaModelUpdate.getLastname() == null || personaModelUpdate.getLastname().equals("")){
            personaModelUpdate.setLastname(persona.get().getLastname());
        }
        if(personaModelUpdate.getCity() == null || personaModelUpdate.getCity().equals("")){
            personaModelUpdate.setCity(persona.get().getCity());
        }
        if(personaModelUpdate.getAge() == null){
            personaModelUpdate.setAge(persona.get().getAge());
        }
        if(personaModelUpdate.getTypeid()== null || personaModelUpdate.getTypeid().equals("")){
            personaModelUpdate.setTypeid(persona.get().getTypeid());
        }
        if(String.valueOf(personaModelUpdate.getNumberid()).length() < 5 || String.valueOf(personaModelUpdate.getNumberid()).length() > 11 ){
            personaModelUpdate.setNumberid(persona.get().getNumberid());
        }

        personaModelUpdate.setId(id);
        personaModelUpdate.setImagen(persona.get().getImagen());

        Persona personaUpdate ;
        personaUpdate = objectMapperConfig.PersonaModelToPersona(personaModelUpdate);   
        personaUpdate = jpaRepository.save(personaUpdate);
        PersonaModel result;
        result = objectMapperConfig.PersonaToPersonaModel(personaUpdate);
        return result;


    }

    @Override
    public String deleteById(Integer id) {
        try {
            jpaRepository.deleteById(id);
            mongoImageRepository.deleteById(id);
            return "Persona eliminada";
        } catch (Exception e) {
            return "No se pudo eliminar la persona";
        }
    }

    @Override
    public List<PersonaModel> findByTypeid(String typeid) {
        List<Persona> persona = jpaRepository.findByTypeid(typeid);
        List<PersonaModel> personaModel = new ArrayList<PersonaModel>();
        persona.forEach(e -> {
            personaModel.add(objectMapperConfig.PersonaToPersonaModel(e));
        });
        return personaModel;
    }

    @Override
    public List<PersonaModel> findByNumberid(Long numberid) {
        List<Persona> persona = jpaRepository.findByNumberid(numberid);
        List<PersonaModel> personaModel = new ArrayList<PersonaModel>();
        persona.forEach(e -> {
            personaModel.add(objectMapperConfig.PersonaToPersonaModel(e));
        });
        return personaModel;
    }

    @Override
    public List<PersonaModel> findByTypeidAndNumberid(String typeid, Long numberid) {
        List<Persona> persona = jpaRepository.findByTypeidAndNumberid(typeid, numberid);
        List<PersonaModel> personaModel = new ArrayList<PersonaModel>();
        persona.forEach(e -> {
            personaModel.add(objectMapperConfig.PersonaToPersonaModel(e));
        });
        return personaModel;
    }

    @Override
    public List<PersonaModel> findByAgeGreaterThanEqual(Integer age) {
        List<Persona> persona = jpaRepository.findByAgeGreaterThanEqual(age);
        List<PersonaModel> personaModel = new ArrayList<PersonaModel>();
        persona.forEach(e -> {
            personaModel.add(objectMapperConfig.PersonaToPersonaModel(e));
        });
        return personaModel;
    }
    
}
