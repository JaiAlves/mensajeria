import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {

    public Recv () throws Exception {
        receiveMessage();
    }

    private void receiveMessage() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Config.getHost());
        factory.setUsername(Config.getUser());
        factory.setPassword(Config.getPassword());
        factory.setPort(Config.getPort());

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Config.getQueueName(), true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        System.out.println("");
        System.out.println("");

        //fica em looping recebendo as msgs (so finaliza se der CTR + C)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Fila :" +Config.getQueueName() +"  Received '" + message + "'");
            System.out.println();
        };
        channel.basicConsume(Config.getQueueName(), true, deliverCallback, consumerTag -> { });

/*      //recebe todas as msgs e finaliza
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
                System.out.println("message received");
            }
        };

        System.out.println("Checando mensagem!");
        channel.basicConsume(Config.getQueueName(), true, consumer);
        System.out.println("Mensagem chacada!");

*/

    }

}