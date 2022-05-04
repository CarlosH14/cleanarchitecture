package co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.pragma.mono.clean.cleanarchitecture.infrastructure.drivenadapters.mongorepository.entities.Imagen;

@Repository
public interface MongoImageRepository extends MongoRepository<Imagen, Integer>{
    
}
