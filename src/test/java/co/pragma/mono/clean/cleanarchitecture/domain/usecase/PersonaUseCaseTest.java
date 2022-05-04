package co.pragma.mono.clean.cleanarchitecture.domain.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.gateways.PersonaModelRepository;

public class PersonaUseCaseTest {

    @Mock
    private PersonaModelRepository personaModelRepository;

    @InjectMocks
    private PersonaUseCase personaUseCase;

    private PersonaModel personaModel;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        
        personaModel = new PersonaModel(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L,1);
    
    }

    @Test
    void testDeleteById() {

        when(personaModelRepository.deleteById(1)).thenReturn("Persona eliminada");
        assertEquals("Persona eliminada", personaUseCase.deleteById(1));

    }

    @Test
    void testFindAll() {

        when(personaModelRepository.findAll()).thenReturn(Arrays.asList(personaModel));
        assertNotNull(personaUseCase.findAll());

    }

    @Test
    void testFindByAgeGreaterThanEqual() {

        when(personaModelRepository.findByAgeGreaterThanEqual(77)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(personaUseCase.findByAgeGreaterThanEqual(77));

    }

    @Test
    void testFindById() {
            
        when(personaModelRepository.findById(1)).thenReturn(Optional.of(personaModel));
        assertNotNull(personaUseCase.findById(1));
    
    }

    @Test
    void testFindByNumberid() {
                
        when(personaModelRepository.findByNumberid(77777L)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(personaUseCase.findByNumberid(77777L));
        
    }

    @Test
    void testFindByTypeid() {
        
        when(personaModelRepository.findByTypeid("TypeTest")).thenReturn(Arrays.asList(personaModel));
        assertNotNull(personaUseCase.findByTypeid("TypeTest"));
        
    }

    @Test
    void testFindByTypeidAndNumberid() {

        when(personaModelRepository.findByTypeidAndNumberid("TypeTest", 77777L)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(personaUseCase.findByTypeidAndNumberid("TypeTest", 77777L));

    }

    @Test
    void testSave() {
            
        when(personaModelRepository.save(personaModel)).thenReturn(personaModel);
        assertNotNull(personaUseCase.save(personaModel));

    }

    @Test
    void testUpdate() {
                
        when(personaModelRepository.update(1, personaModel)).thenReturn(personaModel);
        assertNotNull(personaUseCase.update(1, personaModel));

    }
}
