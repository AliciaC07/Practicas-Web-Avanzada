package practica.mocky.practica2pwa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practica.mocky.practica2pwa.models.User;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsernameAndActiveTrue(String name);

    Iterable<User> findAllByActiveTrue();

    Optional<User> findUserByIdAndActiveTrue(Integer integer);

    Optional<User> findUserByEmailAndActiveTrue(String email);
}
