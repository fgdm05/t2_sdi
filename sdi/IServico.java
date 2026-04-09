import java.rmi.*;

public interface IServico extends Remote {
    // Estoque do navio (mochila)
    public double embarcar(String descricao) throws RemoteException;
    // public String relatorio_embarque() throws RemoteException;

    // Descricao dos navios
    public Integer cadastrar_navio(String descricao, Integer capacidade) throws RemoteException;
    public void remover_navio(Integer id) throws RemoteException;
    public String relatorio_navio() throws RemoteException;

    // Estoque do porto
    public Integer cadastrar_carga(String descricao, Integer volume) throws RemoteException;
    public void remover_carga(Integer id) throws RemoteException;
    public String relatorio_carga() throws RemoteException;
}