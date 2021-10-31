import com.github.javafaker.Faker;
import config.Producer;
import org.apache.activemq.broker.BrokerService;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        int numGenerator = 5;
        int timeDelay = 15;
        if(args.length == 0) {
            System.out.println("You need to pass an option");
            return;
        }

        String option = args[0];
        System.out.println("Option: ---> "+ option);

        if (option.equalsIgnoreCase("1")){
            System.out.println("*******Initializing JMS********");
            BrokerService brokerService = new BrokerService();
            brokerService.addConnector("tcp://0.0.0.0:61616");
            brokerService.start();
        }else if (option.equalsIgnoreCase("2")){
            int idDevice = Integer.parseInt(args[1]);
            if (idDevice == 0 || idDevice < 0){
                System.out.println("You need to pass a valid id device for the temperature");
            }else {
                for (int generating = 0; generating < numGenerator; generating++){
                    System.out.println("*********Initializing Producer*******");
                    try {
                        TimeUnit.SECONDS.sleep(timeDelay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    new Producer().sendMessage(idDevice, "notificacion_sensores");

                }
            }
        }


    }
}
