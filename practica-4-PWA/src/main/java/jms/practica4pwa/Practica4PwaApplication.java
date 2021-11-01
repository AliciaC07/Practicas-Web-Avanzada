package jms.practica4pwa;

import jms.practica4pwa.config.Consumer;
import jms.practica4pwa.controller.MessageController;
import jms.practica4pwa.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

@SpringBootApplication
public class Practica4PwaApplication {

    @Autowired
    MessageService messageService;
    @Autowired
    MessageController messageController;
    public static void main(String[] args) {
        SpringApplication.run(Practica4PwaApplication.class, args);
    }

    @PostConstruct
    public void connectionJms() throws JMSException {
        new Consumer(messageService, messageController).connect();
    }


}
