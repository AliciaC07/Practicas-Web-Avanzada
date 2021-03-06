package jms.practica4pwa.reposotories;

import jms.practica4pwa.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    Optional<Message> findById(Integer id);
}
