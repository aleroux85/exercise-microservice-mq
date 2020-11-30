
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recieve {

    private final static String QUEUE_NAME = "names";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setUsername("username");
        factory.setPassword("password");
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("waiting for messages (CTRL+C to exit)");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            Pattern p = Pattern.compile("Hello my name is, ([a-zA-Z]{2,20})");
            Matcher m = p.matcher(message);
            if (m.matches()) {
                System.out.println("Hello " + m.group(1) + ", I am your father!");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}