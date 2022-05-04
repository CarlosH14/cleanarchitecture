package co.pragma.mono.clean.cleanarchitecture.domain.usecase;

import static org.junit.Assert.*;
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
import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.gateways.ImagenModelRepository;

public class ImagenUseCaseTest {

    @Mock
    private ImagenModelRepository imagenModelRepository;

    @InjectMocks
    private ImagenUseCase imagenUseCase;

    private MultipartFile file;
    ImagenModel imagenModel;

    @BeforeEach
    void setUp() throws IOException{

        MockitoAnnotations.openMocks(this);
        File mfile = new File("C:/Users/carlos.hincapie/Downloads/DF11/11133.png");
        file = new MockMultipartFile("11133.png", Files.readAllBytes(Paths.get(mfile.getPath())));
        imagenModel = new ImagenModel();
        imagenModel.setId(1);
        Binary photo = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        imagenModel.setPhoto(photo);
    }

    @Test
    void testDeleteById() {

        when(imagenModelRepository.deleteById(1)).thenReturn("Imagen eliminada");
        assertEquals("Imagen eliminada", imagenUseCase.deleteById(1));

    }

    @Test
    void testFindAll() {

        when(imagenModelRepository.findAll()).thenReturn(Arrays.asList(imagenModel));
        assertNotNull(imagenUseCase.findAll());

    }

    @Test
    void testFindById() {

        when(imagenModelRepository.findById(1)).thenReturn(Optional.of(imagenModel));
        assertNotNull(imagenUseCase.findById(1));

    }

    @Test
    void testSave() throws IOException {

        when(imagenModelRepository.save(1, file)).thenReturn(imagenModel);
        assertNotNull(imagenUseCase.save(1, file));

    }

    @Test
    void testUpdate() throws IOException {
            
            when(imagenModelRepository.update(1, file)).thenReturn(imagenModel);
            assertNotNull(imagenUseCase.update(1, file));

    }
}
