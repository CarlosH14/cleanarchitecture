package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.application.config.ObjectMapperConfig;
import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.gateways.ImagenModelRepository;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.JPARepository;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities.Imagen;
import lombok.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MongoImageRepositoryAdapter implements ImagenModelRepository{

    @Autowired
    MongoImageRepository mongoImageRepository;
    @Autowired
    JPARepository jpaRepository;

    private ObjectMapperConfig objectMapperConfig = Mappers.getMapper(ObjectMapperConfig.class);

    @Override
    public List<ImagenModel> findAll() {
        List<Imagen> imagen = mongoImageRepository.findAll();
        List<ImagenModel> imagenModel = new ArrayList<ImagenModel>();
        imagen.forEach(e -> {
            imagenModel.add(objectMapperConfig.ImagenToImagenModel(e));
        });
        return imagenModel;
    }

    @Override
    public Optional<ImagenModel> findById(Integer id) {
        Optional<Imagen> imagen = mongoImageRepository.findById(id);
        if(imagen.isPresent()){
            return Optional.of(objectMapperConfig.ImagenToImagenModel(imagen.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public ImagenModel save(Integer id, MultipartFile file) throws IOException {

        Binary photo = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Imagen imagen = new Imagen(id, photo);
        
        Optional<Persona> persona = jpaRepository.findById(id);
        if(persona.isPresent()){
            Persona personaResult = persona.get();
            personaResult.setImagen(id);
            jpaRepository.save(personaResult);
            imagen = mongoImageRepository.save(imagen);
        }else{
            return null;
        }

        ImagenModel imagenModelResult;
        
        imagenModelResult = objectMapperConfig.ImagenToImagenModel(imagen);

        return imagenModelResult;
    }

    @Override
    public ImagenModel update(Integer id, MultipartFile file) throws IOException {
        Optional<Imagen> imagen = mongoImageRepository.findById(id);
        if(imagen.isPresent()){
            Imagen imagenResult = imagen.get();
            Binary photo = new Binary(BsonBinarySubType.BINARY, file.getBytes());
            imagenResult.setId(id);
            imagenResult.setPhoto(photo);
            imagenResult = mongoImageRepository.save(imagenResult);
            return objectMapperConfig.ImagenToImagenModel(imagenResult);
        }else{
        return null;
        }
    }

    @Override
    public String deleteById(Integer id) {
        try {
            mongoImageRepository.deleteById(id);
            jpaRepository.findById(id).ifPresent(e -> {
                e.setImagen(null);
                jpaRepository.save(e);
            });
            return "Imagen eliminada";
        } catch (Exception e) {
            return "No se pudo eliminar la imagen";
        }
        }
        
    
    
}
