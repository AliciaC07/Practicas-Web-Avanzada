package jms.practica4pwa.controller;

import jms.practica4pwa.models.Message;
import jms.practica4pwa.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@Controller
public class MessageController {

    private final MessageService messageService;
    @Autowired
    private SimpMessagingTemplate template;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //@SendTo("/topic/data")
    //@Scheduled(fixedRate = 10000)
    @MessageMapping("/all-data")
    public void findAll(){
        this.template.convertAndSend("/topic/data", messageService.findAll());
    }
    /*public Iterable<Message> findAll(){
        return messageService.findAll();
    }*/

    /*@MessageMapping("/data-co")
    @SendTo("/topic/data")
    public Message findById(Integer id){
        return messageService.findById(id);
    }*/
}
