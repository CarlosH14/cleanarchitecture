package co.pragma.mono.clean.cleanarchitecture.domain.usecase;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.gateways.ImagenModelRepository;
import lombok.AllArgsConstructor;

//@Component
@AllArgsConstructor
public class ImagenUseCase {
    ImagenModelRepository imagenModelRepository;

    public List<ImagenModel> findAll(){return imagenModelRepository.findAll();};
    public Optional<ImagenModel> findById(Integer id){return imagenModelRepository.findById(id);};
    public ImagenModel save(Integer id, MultipartFile file) throws IOException{return imagenModelRepository.save(id, file);};
    public ImagenModel update(Integer id, MultipartFile file) throws IOException{return imagenModelRepository.update(id, file);};
    public String deleteById(Integer id){return imagenModelRepository.deleteById(id);};
}
