package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.application.config.ObjectMapperConfig;
import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.JPARepository;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities.Imagen;

public class MongoImageRepositoryAdapterTest {

    @Mock
    private JPARepository jpaRepository;
    @Mock
    private MongoImageRepository mongoImageRepository;
    @Mock
    private ObjectMapperConfig objectMapperConfig = Mappers.getMapper(ObjectMapperConfig.class);
    @InjectMocks
    private MongoImageRepositoryAdapter mongoImageRepositoryAdapter;
    
    private Persona persona;
    private PersonaModel personaModel;
    private Imagen imagen;
    private ImagenModel imagenModel;
    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {

        MockitoAnnotations.openMocks(this);
        persona = new Persona(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L, null);
        personaModel = new PersonaModel(1,"NameTest","LastNameTest","CityTest",77,"TypeTest",77777L, null);
        imagen = new Imagen();
        imagenModel = new ImagenModel();
        imagenModel.setId(1);
        imagen.setId(1);
        File mfile = new File("C:/Users/carlos.hincapie/Downloads/DF11/11133.png");
        file = new MockMultipartFile("11133.png", Files.readAllBytes(Paths.get(mfile.getPath())));
        Binary photo = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        imagenModel.setPhoto(photo);
        imagen.setPhoto(photo);
    }

    @Test
    void testDeleteById() {

        
        doNothing().when(mongoImageRepository).deleteById(1);
        when(jpaRepository.findById(1)).thenReturn(Optional.of(persona));
        persona.setImagen(null);
        personaModel.setImagen(null);
        when(jpaRepository.save(persona)).thenReturn(persona);
        assertEquals("Imagen eliminada", mongoImageRepositoryAdapter.deleteById(1));
        assertNotEquals(mongoImageRepositoryAdapter.deleteById(2), "No se pudo eliminar la imagen");
    }

    @Test
    void testFindAll() {

        when(mongoImageRepository.findAll()).thenReturn(Arrays.asList(imagen));
        when(objectMapperConfig.ImagenToImagenModel(imagen)).thenReturn(imagenModel);
        assertNotNull(mongoImageRepositoryAdapter.findAll());

    }

    @Test
    void testFindById() {

        when(mongoImageRepository.findById(1)).thenReturn(Optional.of(imagen));
        when(objectMapperConfig.ImagenToImagenModel(imagen)).thenReturn(imagenModel);
        assertNotNull(mongoImageRepositoryAdapter.findById(1));
        assertEquals(Optional.empty(), mongoImageRepositoryAdapter.findById(2));

    }

    @Test
    void testSave() throws IOException {

        when(mongoImageRepository.save(imagen)).thenReturn(imagen);
        when(jpaRepository.findById(1)).thenReturn(Optional.of(persona));
        persona.setImagen(1);
        personaModel.setImagen(1);
        when(jpaRepository.save(persona)).thenReturn(persona);
        when(objectMapperConfig.ImagenModelToImagen(imagenModel)).thenReturn(imagen);
        when(objectMapperConfig.ImagenToImagenModel(imagen)).thenReturn(imagenModel);
        assertEquals(imagenModel, mongoImageRepositoryAdapter.save(1, file));
        assertNull(mongoImageRepositoryAdapter.save(2, file));
    }

    @Test
    void testUpdate() throws IOException {

        when(mongoImageRepository.findById(1)).thenReturn(Optional.of(imagen));
        when(objectMapperConfig.ImagenToImagenModel(imagen)).thenReturn(imagenModel);
        when(mongoImageRepository.save(imagen)).thenReturn(imagen);
        assertNotNull(mongoImageRepositoryAdapter.update(1, file));
        assertNull(mongoImageRepositoryAdapter.update(2, file));
    }
}
