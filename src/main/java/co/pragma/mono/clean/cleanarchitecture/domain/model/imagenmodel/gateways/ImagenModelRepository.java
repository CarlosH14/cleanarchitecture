package co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.gateways;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;


//@Repository
public interface ImagenModelRepository {
    
    List<ImagenModel> findAll();
    Optional<ImagenModel> findById(Integer id);
    ImagenModel save(Integer id, MultipartFile file) throws IOException;
    ImagenModel update(Integer id, MultipartFile file) throws IOException;
    String deleteById(Integer id);
}
