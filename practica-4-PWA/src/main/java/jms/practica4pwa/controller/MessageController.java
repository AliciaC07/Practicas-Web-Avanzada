package jms.practica4pwa.controller;

import jms.practica4pwa.models.Message;
import jms.practica4pwa.services.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/all-data")
    @SendTo("/topic/data")
    public Iterable<Message> findAll(){
        return messageService.findAll();
    }

    @MessageMapping("/data-co")
    @SendTo("/topic/data")
    public Message findById(Integer id){
        return messageService.findById(id);
    }
}
