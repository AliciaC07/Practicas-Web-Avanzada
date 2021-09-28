package practica.mocky.practica2pwa.repositories;

import org.springframework.data.repository.CrudRepository;
import practica.mocky.practica2pwa.models.Role;

import java.util.Optional;

public interface RoleRepossitory extends CrudRepository<Role, Integer> {
    Optional<Role> findByNameAndActiveTrue(String name);

    Iterable<Role> findAllByActiveTrue();

}
