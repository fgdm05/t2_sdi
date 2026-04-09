/** HelloClient.java **/
import java.rmi.registry.*;
public class PortoClient {
   public static void main(String[] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         // Obtém uma referência para o registro do RMI
         Registry registry = LocateRegistry.getRegistry(host,6655);

         // Obtém a stub do servidor
         //HelloWorld stub= (HelloWorld) registry.lookup("Hello");
         IServico stub2 = (IServico) registry.lookup("Porto");

         // Chama o método do servidor e imprime a mensagem
         //String msg = stub.hello();
         Integer valor = stub2.cadastrar_navio("Navio A", 1000);
         stub2.cadastrar_navio("Navio B", 2000);
         stub2.remover_navio(1);
         String relatorio = stub2.relatorio_navio();

         stub2.cadastrar_carga("Feijao", 20);
         stub2.cadastrar_carga("Arroz", 30);
         String rel = stub2.relatorio_carga();
         stub2.remover_carga(1);
         String rel2 = stub2.relatorio_carga();
         
         System.out.println("Mensagem do Servidor: " + valor);
         System.out.println(relatorio);
         System.out.println(rel);
         System.out.println(rel2);

      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
