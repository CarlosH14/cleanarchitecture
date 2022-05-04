package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.jparepository.entities.Persona;

@Repository
public interface JPARepository extends JpaRepository<Persona, Integer>{
    
        List<Persona> findByTypeid(String typeid);
        List<Persona> findByNumberid(Long numberid);
        List<Persona> findByTypeidAndNumberid(String typeid, Long numberid);
        List<Persona> findByAgeGreaterThanEqual(Integer age);
}
