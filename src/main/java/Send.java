import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public Send() throws IOException, TimeoutException {
        sendMessage();
    }

    private void sendMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.getHost());
        factory.setUsername(Config.getUser());
        factory.setPassword(Config.getPassword());
        factory.setPort(Config.getPort());
        String routingKey="";

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(Config.getExchangeName(), "direct", true);
            channel.queueDeclare(Config.getQueueName(), true, false, false, null);
            channel.queueBind(Config.getQueueName(), Config.getExchangeName(), routingKey);

            byte[] messageBodyBytes = Config.getMessage().getBytes();

            channel.basicPublish(Config.getExchangeName(), routingKey,
                    new AMQP.BasicProperties.Builder()
                            .deliveryMode(1)
                            .build(),
                    messageBodyBytes);

            System.out.println(" [x] Sent '" + Config.getMessage() + "'");
            System.out.println("Mensagem enviada ...");
        }
    }

}
