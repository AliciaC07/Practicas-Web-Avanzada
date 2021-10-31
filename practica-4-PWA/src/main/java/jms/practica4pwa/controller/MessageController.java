package jms.practica4pwa.controller;

import jms.practica4pwa.models.Message;
import jms.practica4pwa.services.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all-data")
    public Iterable<Message> findAll(){
        return messageService.findAll();
    }

    @GetMapping("/data/{id}")
    public Message findById(@PathVariable Integer id){
        return messageService.findById(id);
    }
}
