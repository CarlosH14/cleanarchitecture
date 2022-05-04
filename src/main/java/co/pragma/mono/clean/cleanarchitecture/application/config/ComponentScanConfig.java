package co.pragma.mono.clean.cleanarchitecture.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.gateways.ImagenModelRepository;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.gateways.PersonaModelRepository;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.ImagenUseCase;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.PersonaUseCase;

import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.pragma.mono.clean.architecture.domain.usecase",
        includeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class ComponentScanConfig {
    @Bean
    public PersonaUseCase personaUseCase(PersonaModelRepository personaModelRepository) {
        return new PersonaUseCase(personaModelRepository);
    }
    @Bean
    public ImagenUseCase imagenUseCase(ImagenModelRepository imagenModelRepository) {
        return new ImagenUseCase(imagenModelRepository);
    }
}
