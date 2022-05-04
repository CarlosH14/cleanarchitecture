package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.pragma.mono.clean.cleanarchitecture.application.config.ObjectMapperConfig;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.MongoImageRepository;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities.Imagen;

public class JPARepositoryAdapterTest {

    @Mock
    private JPARepository jpaRepository;
    @Mock
    private MongoImageRepository mongoImageRepository;
    @Mock
    private ObjectMapperConfig objectMapperConfig = Mappers.getMapper(ObjectMapperConfig.class);
    @InjectMocks
    private JPARepositoryAdapter jpaRepositoryAdapter;
    
    private Persona persona;
    private PersonaModel personaModel;
    private Imagen imagen;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        persona = new Persona(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L,1);
        personaModel = new PersonaModel(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L,1);
        imagen = new Imagen();
        imagen.setId(1);

    }

    @Test
    void testDeleteById() {

        doNothing().when(jpaRepository).deleteById(1);
        doNothing().when(mongoImageRepository).deleteById(1);
        assertEquals(jpaRepositoryAdapter.deleteById(1), "Persona eliminada");
        assertNotEquals(jpaRepositoryAdapter.deleteById(2), "No se pudo eliminar la persona");

    }

    @Test
    void testFindAll() {

        when(jpaRepository.findAll()).thenReturn(Arrays.asList(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findAll());
    }

    @Test
    void testFindByAgeGreaterThanEqual() {
        when(jpaRepository.findByAgeGreaterThanEqual(77)).thenReturn(Arrays.asList(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findByAgeGreaterThanEqual(77));

    }

    @Test
    void testFindById() {
        when(jpaRepository.findById(1)).thenReturn(Optional.of(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findById(1));
        assertEquals(Optional.empty(), jpaRepositoryAdapter.findById(2));

    }

    @Test
    void testFindByNumberid() {

        when(jpaRepository.findByNumberid(77777L)).thenReturn(Arrays.asList(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findByNumberid(77777L));

    }

    @Test
    void testFindByTypeid() {

        when(jpaRepository.findByTypeid("TypeTest")).thenReturn(Arrays.asList(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findByTypeid("TypeTest"));


    }

    @Test
    void testFindByTypeidAndNumberid() {

        when(jpaRepository.findByTypeidAndNumberid("TypeTest",77777L)).thenReturn(Arrays.asList(persona));
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.findByTypeidAndNumberid("TypeTest",77777L));


    }

    @Test
    void testSave() {

        when(jpaRepository.save(persona)).thenReturn(persona);
        when(objectMapperConfig.PersonaModelToPersona(personaModel)).thenReturn(persona);
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        assertNotNull(jpaRepositoryAdapter.save(personaModel));

    }

    @Test
    void testUpdate() {

        when(jpaRepository.findById(1)).thenReturn(Optional.of(persona));
        //assertNull(Optional.empty());

        assertNotNull(personaModel.getName());
        personaModel.setName(null);
        assertNull(personaModel.getName());
        personaModel.setName(persona.getName());

        assertNotNull(personaModel.getLastname());
        personaModel.setLastname(null);
        assertNull(personaModel.getLastname());
        personaModel.setLastname(persona.getLastname());

        assertNotNull(personaModel.getCity());
        personaModel.setCity(null);
        assertNull(personaModel.getCity());
        personaModel.setCity(persona.getCity());

        assertNotNull(personaModel.getAge());
        personaModel.setAge(null);
        assertNull(personaModel.getAge());
        personaModel.setAge(persona.getAge());

        assertNotNull(personaModel.getTypeid());
        personaModel.setTypeid(null);
        assertNull(personaModel.getTypeid());
        personaModel.setTypeid(persona.getTypeid());
        
        personaModel.setId(1);
        personaModel.setImagen(1);

        when(objectMapperConfig.PersonaModelToPersona(personaModel)).thenReturn(persona);
        when(objectMapperConfig.PersonaToPersonaModel(persona)).thenReturn(personaModel);
        when(jpaRepository.save(persona)).thenReturn(persona);
        assertNotNull(jpaRepositoryAdapter.update(1, personaModel));


    }
}
