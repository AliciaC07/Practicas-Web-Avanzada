package practica.mocky.practica2pwa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practica.mocky.practica2pwa.models.HttpStatusCode;

import java.util.Optional;

@Repository
public interface HttpsStatusCodeRepository extends CrudRepository<HttpStatusCode, Integer> {
    Optional<HttpStatusCode> findHttpStatusCodeByCode(Integer code);
    Optional<HttpStatusCode> findHttpStatusCodeByName(String name);

    Iterable<HttpStatusCode> findHttpStatusCodeByType(String name);

}
