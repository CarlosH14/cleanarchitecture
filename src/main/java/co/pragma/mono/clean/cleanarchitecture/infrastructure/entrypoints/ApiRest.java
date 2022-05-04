package co.pragma.mono.clean.cleanarchitecture.infrastructure.entrypoints;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import co.pragma.mono.clean.cleanarchitecture.domain.model.imagenmodel.ImagenModel;
import co.pragma.mono.clean.cleanarchitecture.domain.model.personamodel.PersonaModel;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.ImagenUseCase;
import co.pragma.mono.clean.cleanarchitecture.domain.usecase.PersonaUseCase;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.*;
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class ApiRest {

    @Autowired
    private PersonaUseCase personaUseCase;
    @Autowired
    private ImagenUseCase imagenUseCase;

    @GetMapping("/path")
    public String commandName() {
        return "Hello World";
    }
    // ---------- CREATE ----------
    @PostMapping("/persona")
    public PersonaModel savePersona(@RequestBody PersonaModel personaModel) {
        return personaUseCase.save(personaModel);
    }

    @PostMapping("/imagen")
    public ImagenModel saveImagen(@RequestParam("id") Integer id, @RequestParam("photo") MultipartFile file) throws IOException {
        return imagenUseCase.save(id, file);
    }
    // ---------- FIND ALL ----------
    @GetMapping("/persona")
    public List<PersonaModel> findAllPersona() {
        return personaUseCase.findAll();
    }

    @GetMapping("/imagen")
    public List<ImagenModel> findAllImagen() {
        return imagenUseCase.findAll();
    }
    // ---------- FIND BY ID ----------
    @GetMapping("/persona/{id}")
    public Optional<PersonaModel> findByIdPersona(@PathVariable Integer id) {
        return personaUseCase.findById(id);
    }

    @GetMapping("/imagen/{id}")
    public Optional<ImagenModel> findByIdImagen(@PathVariable Integer id) {
        return imagenUseCase.findById(id);
    }
    // ---------- UPDATE ----------
    @PutMapping("/persona/{id}")
    public PersonaModel updatePersona(@PathVariable Integer id, @RequestBody PersonaModel personaModelUpdate) {
        return personaUseCase.update(id, personaModelUpdate);
    }

    @PutMapping("/imagen/{id}")
    public ImagenModel updateImagen(@PathVariable Integer id, @RequestParam("photo") MultipartFile file) throws IOException {
        return imagenUseCase.update(id, file);
    }
    // ---------- DELETE ----------
    @DeleteMapping("/persona/{id}")
    public String deletePersona(@PathVariable Integer id) {
        return personaUseCase.deleteById(id);
    }

    @DeleteMapping("/imagen/{id}")
    public String deleteImagen(@PathVariable Integer id) {
        return imagenUseCase.deleteById(id);
    }
    // ---------- FIND BY TYPEID ----------
    @GetMapping("/persona/typeid/{typeid}")
    public List<PersonaModel> findByTypeidPersona(@PathVariable String typeid) {
        return personaUseCase.findByTypeid(typeid);
    }

    // ---------- FIND BY NUMBERID ----------
    @GetMapping("/persona/numberid/{numberid}")
    public List<PersonaModel> findByNumberidPersona(@PathVariable Long numberid) {
        return personaUseCase.findByNumberid(numberid);
    }

    // ---------- FIND BY TYPEID AND NUMBERID ----------
    @GetMapping("/persona/typeid/{typeid}/numberid/{numberid}")
    public List<PersonaModel> findByTypeidAndNumberidPersona(@PathVariable String typeid, @PathVariable Long numberid) {
        return personaUseCase.findByTypeidAndNumberid(typeid, numberid);
    }

    // ---------- FIND BY AGE GREATER THAN EQUAL ----------
    @GetMapping("/persona/age/{age}")
    public List<PersonaModel> findByAgeGreaterThanEqualPersona(@PathVariable Integer age) {
        return personaUseCase.findByAgeGreaterThanEqual(age);
    }

}
