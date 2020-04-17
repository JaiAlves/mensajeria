import java.util.HashMap;
import java.util.Map;

public class Principal {
    public static void main(String[] argv) throws Exception {

        Map<String, String> mapaParametros = new HashMap<String, String>();

        if (argv != null) {
            String cfg = argv[0];
            String aux[] = cfg.split(";");

            for (int i = 0; i < aux.length; i++) {
                int divisao = aux[i].indexOf("=");
                String chave = aux[i].substring(0, divisao);
                String valor = aux[i].substring((divisao + 1), aux[i].length());

                System.out.println(chave.toUpperCase() + "              : " + valor);
                mapaParametros.put(chave.toUpperCase(), valor);
            }
        }
        Config config = new Config(mapaParametros);

        if (mapaParametros.get("METHOD").equalsIgnoreCase("SEND")) {
            Send send = new Send();
        } else if (mapaParametros.get("METHOD").equalsIgnoreCase("RECEIVE")) {
            Recv recv = new Recv();
        } else {
            System.out.print("Função desconhecida!");
        }
    }
}
