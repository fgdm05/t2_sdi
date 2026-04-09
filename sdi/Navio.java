public class Navio implements Comparable<Navio>{
    private String descricao;
    private int capacidade;
    private Integer id;

    public Navio(Integer id, String descricao, int capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Integer getId() {
        return id;
    }

    public int compareTo(Navio n) {
        return Integer.compare(capacidade, n.getCapacidade());
    }
}