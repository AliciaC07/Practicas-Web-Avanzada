package jms.practica4pwa.reposotories;

import jms.practica4pwa.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Integer, Message> {
}
