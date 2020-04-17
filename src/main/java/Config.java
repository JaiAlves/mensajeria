import java.util.HashMap;
import java.util.Map;

public class Config {

    private static Map<String, String> mapaParametros = new HashMap<String, String>();

    private static String HOST="10.97.55.162";
    private static String USER="rabbitmq";
    private static String PASSWORD="123456";
    private static String PORT="5672";

    //receive
    private static String QUEUE_NAME="queue.monitoring.notification";
    private static String EXCHANGE_NAME="";

    //send
    private static String QUEUE_SEND_NAME="queue.monitoring.negative.market-record";
    private static String EXCHANGE_SEND_NAME="exchange.monitoring.negative.market-record";

    public Config(Map<String, String> mapaParametros) {
        this.mapaParametros.putAll(mapaParametros);
        printCfgs();
    }

    public static String getHost() {
        return mapaParametros.containsKey("HOST")?mapaParametros.get("HOST"):HOST;
    }

    public static String getUser() {
        return mapaParametros.containsKey("USER")?mapaParametros.get("USER"):USER;
    }

    public static String getPassword() {
        return mapaParametros.containsKey("PASSWORD")?mapaParametros.get("PASSWORD"):PASSWORD;
    }

    public static int getPort() {
        return Integer.parseInt(mapaParametros.containsKey("PORT")?mapaParametros.get("PORT"):PORT);
    }

    public static String getQueueName() {
        if (mapaParametros.get("METHOD").equalsIgnoreCase("SEND")) {
            return mapaParametros.containsKey("QUEUE_NAME") ? mapaParametros.get("QUEUE_NAME") : QUEUE_SEND_NAME;
        } else {
            return mapaParametros.containsKey("QUEUE_NAME") ? mapaParametros.get("QUEUE_NAME") : QUEUE_NAME;
        }
    }

    public static String getExchangeName() {
        if (mapaParametros.get("METHOD").equalsIgnoreCase("SEND")) {
            return mapaParametros.containsKey("EXCHANGE_NAME") ? mapaParametros.get("EXCHANGE_NAME") : EXCHANGE_SEND_NAME;
        } else {
            return mapaParametros.containsKey("EXCHANGE_NAME") ? mapaParametros.get("EXCHANGE_NAME") : EXCHANGE_NAME;
        }
    }

    public static String getMessage() {
        return mapaParametros.get("MESSAGE");
    }

    private void printCfgs() {
        System.out.println("HOST                        :"+getHost());
        System.out.println("USER                        :"+getUser());
        System.out.println("PASSWORD                    :"+getPassword());
        System.out.println("PORT                        :"+getPort());
        System.out.println("QUEUE_NAME                  :"+getQueueName());
        System.out.println("EXCHANGE_NAME               :"+getExchangeName());
    }

}
