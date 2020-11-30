import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.io.Console;  

class Send { 
    private final static String QUEUE_NAME = "names";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setUsername("username");
        factory.setPassword("password");

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String name;   
            Console con = System.console();   
            if(con == null)    
            {   
                System.out.print("no console available");   
                return;   
            }   
            name = con.readLine("enter your name: "); 
            channel.basicPublish("", QUEUE_NAME, null, name.getBytes(StandardCharsets.UTF_8));
            System.out.println("Hello my name is, " + name);
        }
    }
}