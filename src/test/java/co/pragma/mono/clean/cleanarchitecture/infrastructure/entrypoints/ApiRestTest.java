package co.pragma.mono.clean.cleanarchitecture.infrastructure.entrypoints;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.ImagenUseCase;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.PersonaUseCase;

public class ApiRestTest {

    @Mock
    private PersonaUseCase personaUseCase;
    @Mock
    private ImagenUseCase imagenUseCase;

    @InjectMocks
    private ApiRest apiRest;

    private PersonaModel personaModel;
    private MultipartFile file;
    private ImagenModel imagenModel;

    @BeforeEach
    void setUp() throws IOException {

        MockitoAnnotations.openMocks(this);
        imagenModel = new ImagenModel();
        personaModel = new PersonaModel(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L,1);
        File mfile = new File("C:/Users/carlos.hincapie/Downloads/DF11/11133.png");
        file = new MockMultipartFile("11133.png", Files.readAllBytes(Paths.get(mfile.getPath())));
        imagenModel.setId(1);
        Binary photo = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        imagenModel.setPhoto(photo);
    }

    @Test
    void testCommandName() {
        assertEquals("Hello World", apiRest.commandName());
    }

    @Test
    void testDeleteImagen() {
        when(imagenUseCase.deleteById(1)).thenReturn("Imagen eliminada");
        assertEquals("Imagen eliminada", apiRest.deleteImagen(1));
    }

    @Test
    void testDeletePersona() {
        when(personaUseCase.deleteById(1)).thenReturn("Persona eliminada");
        assertEquals("Persona eliminada", apiRest.deletePersona(1));

    }

    @Test
    void testFindAllImagen() {
        when(imagenUseCase.findAll()).thenReturn(Arrays.asList(imagenModel));
        assertNotNull(apiRest.findAllImagen());

    }

    @Test
    void testFindAllPersona() {
        when(personaUseCase.findAll()).thenReturn(Arrays.asList(personaModel));
        assertNotNull(apiRest.findAllPersona());
    }

    @Test
    void testFindByAgeGreaterThanEqualPersona() {
        when(personaUseCase.findByAgeGreaterThanEqual(77)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(apiRest.findByAgeGreaterThanEqualPersona(77));
    }

    @Test
    void testFindByIdImagen() {
        when(imagenUseCase.findById(1)).thenReturn(Optional.of(imagenModel));
        assertNotNull(apiRest.findByIdImagen(1));

    }

    @Test
    void testFindByIdPersona() {
        when(personaUseCase.findById(1)).thenReturn(Optional.of(personaModel));
        assertNotNull(apiRest.findByIdPersona(1));

    }

    @Test
    void testFindByNumberidPersona() {
        when(personaUseCase.findByNumberid(77777L)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(apiRest.findByNumberidPersona(77777L));


    }

    @Test
    void testFindByTypeidAndNumberidPersona() {
        when(personaUseCase.findByTypeidAndNumberid("TypeTest", 77777L)).thenReturn(Arrays.asList(personaModel));
        assertNotNull(apiRest.findByTypeidAndNumberidPersona("TypeTest", 77777L));

    }

    @Test
    void testFindByTypeidPersona() {
        when(personaUseCase.findByTypeid("TypeTest")).thenReturn(Arrays.asList(personaModel));
        assertNotNull(apiRest.findByTypeidPersona("TypeTest"));

    }

    @Test
    void testSaveImagen() throws IOException {
        when(imagenUseCase.save(1, file)).thenReturn(imagenModel);
        assertNotNull(apiRest.saveImagen(1, file));

    }

    @Test
    void testSavePersona() {
        when(personaUseCase.save(personaModel)).thenReturn(personaModel);
        assertNotNull(apiRest.savePersona(personaModel));

    }

    @Test
    void testUpdateImagen() throws IOException {
        when(imagenUseCase.update(1, file)).thenReturn(imagenModel);
        assertNotNull(apiRest.updateImagen(1, file));

    }

    @Test
    void testUpdatePersona() {
        when(personaUseCase.update(1, personaModel)).thenReturn(personaModel);
        assertNotNull(apiRest.updatePersona(1, personaModel));

    }
}
