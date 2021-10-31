package config;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import models.Message;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.ietf.jgss.GSSContext;

import javax.jms.*;
import java.time.LocalDateTime;

public class Producer {

    public Producer() {
    }

    public void sendMessage(Integer idDevice, String tail) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection("alicia", "1234");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(tail);
        MessageProducer messageProducer = session.createProducer(topic);

        ///Contruccion del mensaje de los dispositivos
        Faker faker = new Faker();
        Message message = new Message();
        message.setIdDevice(idDevice);
        String temp = faker.weather().temperatureFahrenheit();
//        System.out.println(faker.weather().temperatureCelsius());
        temp = temp.replaceAll("[°F]*", "");
        message.setTemperature(Integer.parseInt(temp));
        message.setHumidity(faker.number().numberBetween(0, 100));
        message.setDateGeneration(LocalDateTime.now());

        Gson gson = new Gson();
        String messageToJson = gson.toJson(message);
        TextMessage textMessage = session.createTextMessage(messageToJson);
        messageProducer.send(textMessage);

        messageProducer.close();
        session.close();
        connection.close();





    }
}
