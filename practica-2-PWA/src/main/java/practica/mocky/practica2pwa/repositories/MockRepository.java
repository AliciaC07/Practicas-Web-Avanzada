package practica.mocky.practica2pwa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practica.mocky.practica2pwa.models.Mock;

import java.util.Optional;

@Repository
public interface MockRepository extends CrudRepository<Mock, Integer> {

    Optional<Mock> findMockByMockId(String name);
    Optional<Mock> findMockByIdAndActiveTrue(Integer id);
    Optional<Mock> findMockByJwtValidationAndActiveTrue(String token);




}
