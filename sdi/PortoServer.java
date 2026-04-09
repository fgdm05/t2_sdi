import java.rmi.*;
import java.util.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class PortoServer implements IServico {
    static int id_embarque = 1;
    static int id_navio = 1;
    static int id_carga = 1;
    static List<Navio> navios = new ArrayList<>();
    static List<Carga> cargas = new ArrayList<>();
    static List<Embarque> embarques = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Instancia o objeto servidor e a sua stub
            PortoServer server = new PortoServer();
            IServico stub = (IServico) UnicastRemoteObject.exportObject(server, 0);
            // Registra a stub no RMI Registry para que ela seja obtAida pelos clientes
            Registry registry = LocateRegistry.createRegistry(6655);
            registry.bind("Porto", stub);
            System.out.println("Servidor pronto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public double embarcar(String descricao) throws RemoteException {
        // ... (seu código inicial de somatório e cópia das listas se mantém igual)
        List<Carga> c_cargas = new ArrayList<>(cargas); 
        List<Navio> c_navios = new ArrayList<>(navios); 
        List<Carga> selecionadas_total = new ArrayList<>();
        Map<Navio, List<Carga>> mapa = new HashMap<>();
        
        Collections.sort(c_navios);
        
        for(int i = 0; i < c_navios.size(); i++) {
            mapa.put(c_navios.get(i), new ArrayList<>());
        }

        for(Navio n : c_navios) {
            int nItens = c_cargas.size();
            int capacidade = n.getCapacidade();
            int[][] matriz = new int[nItens + 1][capacidade + 1];

            // Montagem da matriz (0/1 Knapsack)
            for (int i = 1; i <= nItens; i++) {
                int volume = (int) c_cargas.get(i - 1).getVolume();

                for (int j = 1; j <= capacidade; j++) {
                    if (volume > j) {
                        matriz[i][j] = matriz[i - 1][j];
                    } else {
                        matriz[i][j] = Math.max(
                            matriz[i - 1][j],
                            matriz[i - 1][j - volume] + volume 
                        );
                    }
                }
            }

            List<Carga> selecionadas = new ArrayList<>();
            int i = nItens;
            int j = capacidade;

            // CORREÇÃO 1: Usar c_cargas no lugar de cargas
            while (i > 0 && j > 0) {
                int volume = (int) c_cargas.get(i - 1).getVolume(); // Corrigido aqui

                if (matriz[i][j] != matriz[i - 1][j]) {
                    Carga c = c_cargas.get(i - 1); // Corrigido aqui
                    selecionadas.add(c);
                    j -= volume; 
                }
                i--;
            }
            
            // CORREÇÃO 2: Inserir a lista preenchida de volta no mapa
            mapa.put(n, selecionadas); 
            
            c_cargas.removeAll(selecionadas);  
            selecionadas_total.addAll(selecionadas);
        }

        int soma_carga = 0;
        int soma_navio = 0;
        
        for (Carga c : selecionadas_total) {
            soma_carga += c.getVolume();
        }
        
        for(Navio n : mapa.keySet()) {
            if (!mapa.get(n).isEmpty()) { // Simplificado
                soma_navio += n.getCapacidade();
            }
        }
        
        if (!c_cargas.isEmpty()) {
            Navio sobrou = new Navio(-1, "Sobrou", -1);
            mapa.put(sobrou, new ArrayList<>(c_cargas));
        }

        // CORREÇÃO 3: Prevenção de divisão por zero
        double proporcao = 0.0;
        if (soma_navio > 0) {
            proporcao = (double) soma_carga / soma_navio;
        }

        embarques.add(new Embarque(id_embarque, descricao, mapa, proporcao));
        id_embarque++;
        
        return proporcao;
    }

    public String relatorio_embarque() throws RemoteException {
        String f = "";
        for(Embarque embarque : embarques) {
            f += embarque.getDescricao() + "\n";
            Map<Navio,List<Carga>> mapa_embarque = embarque.getMapa();
            for (Navio n : mapa_embarque.keySet()) {
                List<Carga> lista_cargas = mapa_embarque.get(n); 
                f += "Navio: " + n.getDescricao() + ", Capacidade: " + n.getCapacidade() + " - Itens: [";
                if(lista_cargas.size() != 0) {
                    for (Carga c : lista_cargas) {
                        f += "Item: " + c.getDescricao() + ", Volume: " + c.getVolume() + ";";
                    }
                }
                f += "]\n";
            }
                //  embarque.getMapa().toString();
        }
        return f;
    }
    
    
    public Integer cadastrar_navio(String descricao, Integer capacidade) throws RemoteException {
        Navio navio = new Navio(id_navio, descricao, capacidade);
        navios.add(navio);
        id_navio++;
        return id_navio - 1;
    }
    
    public void remover_navio(Integer id) throws RemoteException {
        navios.removeIf(n -> n.getId().equals(id));
    }
    public String relatorio_navio() throws RemoteException {
        String f = "";
        for(Navio navio : navios) {
            f += navio.getId() + " - " + navio.getDescricao() + " - " + navio.getCapacidade() + "\n";
        }
        return f;
    };

    public Integer cadastrar_carga(String descricao, Integer volume) throws RemoteException
    {
        Carga c = new Carga(id_carga, descricao, volume);
        cargas.add(c);
        id_carga++;
        return id_carga - 1;
    };
    
    public void remover_carga(Integer id) throws RemoteException {
        cargas.removeIf(n -> n.getId().equals(id));
        
        
        // for (Carga cargaRemovida : cargas) {
        //     if (cargaRemovida.getId().equals(id)) {
        //         int id_navio_cr = cargaRemovida.getIdNavio();
        //         int volume_cr = cargaRemovida.getVolume();
        //         cargas.removeIf(n -> n.getId().equals(id));
        //         return;
        //     }
        // }
        
    }
    public String relatorio_carga() throws RemoteException {
        String f = "";
        for(Carga carga : cargas) {
            f += carga.getId() + " - " + carga.getDescricao() + " - " + carga.getVolume() + "\n";
        }
        return f;
    };


    
}