package jms.practica4pwa.services;

import jms.practica4pwa.models.Message;
import jms.practica4pwa.reposotories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public Iterable<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Integer id){
        return messageRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("This data was not found"));
    }
}
