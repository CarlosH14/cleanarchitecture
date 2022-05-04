package co.pragma.mono.clean.cleanarchitecture.application.config;

import org.mapstruct.Mapper;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;
import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities.Imagen;

@Mapper
public interface ObjectMapperConfig{

    //ToData
    public PersonaModel PersonaToPersonaModel(Persona e);
    public ImagenModel ImagenToImagenModel(Imagen e);

    //ToEntity
    public Persona PersonaModelToPersona(PersonaModel d);
    public Imagen ImagenModelToImagen(ImagenModel d);

}
