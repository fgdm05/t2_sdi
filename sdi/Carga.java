public class Carga {
    private String descricao;
    private Integer volume;
    private Integer id;
    // private Integer id_navio = -1;

    public Carga() {}
    public Carga(Integer id, String descricao, Integer volume) {
        this.id = id;
        this.descricao = descricao;
        this.volume = volume;
    }
    // public Carga(Integer id, String descricao, Integer volume, Integer id_navio) {
    //     this.id = id;
    //     this.descricao = descricao;
    //     this.volume = volume;
    //     this.id_navio = id_navio;
    // }

    // public Integer getIdNavio() {return id_navio;}
    // public void setIdNavio(int id_navio) {this.id_navio = id_navio;}
    public String getDescricao() {return descricao;}
    public Integer getVolume() {return volume;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setVolume(Integer volume) {this.volume=volume;}
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id=id;}

}