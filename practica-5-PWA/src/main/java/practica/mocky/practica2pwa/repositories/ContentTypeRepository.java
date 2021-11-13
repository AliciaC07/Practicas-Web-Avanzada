package practica.mocky.practica2pwa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practica.mocky.practica2pwa.models.ContentType;

import java.util.Optional;

@Repository
public interface ContentTypeRepository extends CrudRepository<ContentType, Integer> {
    Optional<ContentType> findByName(String name);
}
