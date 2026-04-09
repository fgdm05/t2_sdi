import java.util.*;

public class Embarque {
    private int id;
    private String descricao;
    private Map<Navio,List<Carga>> mapa;
    private double proporcao_carga_navio;

    public Embarque(int id, String descricao, Map<Navio, List<Carga>> mapa, double proporcao_carga_navio) {
        this.id = id;
        this.descricao = descricao;
        this.mapa = mapa;
        this.proporcao_carga_navio = proporcao_carga_navio;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Map<Navio, List<Carga>> getMapa() {
        return mapa;
    }

    @Override
    public String toString() {
        return "Embarque [id=" + id + ", descricao=" + descricao + ", mapa=" + mapa + ", proporcao_carga_navio="
                + proporcao_carga_navio + "]";
    }
    
    
}