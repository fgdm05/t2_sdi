
import java.rmi.*;
import java.util.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class PortoServer implements IServico {
    
    static int id_navio = 1;
    static int id_carga = 1;
    static List<Navio> navios = new ArrayList<>();
    static List<Carga> cargas = new ArrayList<>();

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
    // public double embarcar(String descricao) throws RemoteException;
    // public String relatorio_embarque() throws RemoteException;

    
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
        String f = "RELATORIO NAVIOS\n";
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
        String f = "RELATORIO CARGAS\n";
        for(Carga carga : cargas) {
            f += carga.getId() + " - " + carga.getDescricao() + " - " + carga.getVolume() + "\n";
        }
        return f;
    };

    public double embarcar(String descricao) throws RemoteException {
        double s_cargas = 0;
        for(Carga c : cargas) {
            s_cargas += c.getVolume();
        }
        
        List<Carga> c_cargas = new ArrayList<>(cargas);
        List<Navio> c_navios = new ArrayList<>(navios);
        Collections.sort(c_navios);

        double max_cap = 0;
        for(Navio n : c_navios) {
            
        }




        if(s_cargas <= max_cap) {
            
            
            return s_cargas;
        } else {
            throw new CapacidadeIndisponivelException("Capacidade indisponível para embarque");
        }



        return 0;
    }


}