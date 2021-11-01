package jms.practica4pwa.config;

import com.google.gson.Gson;
import jms.practica4pwa.controller.MessageController;
import jms.practica4pwa.models.Message;
import jms.practica4pwa.services.MessageService;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    ActiveMQConnectionFactory factory;
    Connection connection;
    Session session;
    Queue queue;
    Topic topic;
    Gson gson = new Gson();
    MessageConsumer messageConsumer;
    MessageController messageController;
    MessageService messageService;

    public Consumer(MessageService messageService, MessageController messageController) {
        this.messageController = messageController;
        this.messageService = messageService;
    }

    public void connect() throws JMSException {
        factory = new ActiveMQConnectionFactory("alicia","1234", "failover:tcp://localhost:61616");
        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic("notificacion_sensores");
        messageConsumer = session.createConsumer(topic);
        messageConsumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Receive message: \n\n"+ textMessage.getText());
                Message messageReceive = gson.fromJson(textMessage.getText(), Message.class);
                messageService.save(messageReceive);

            } catch (JMSException e) {
                e.printStackTrace();
            }

            messageController.findAll();
        });
    }

    public void disconnect() throws JMSException {
        connection.stop();
        connection.close();
    }
}
