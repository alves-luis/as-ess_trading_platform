package business.ativos;

public class Commodity extends Ativo {

    private String pais;

    public Commodity(String id, String nome, double vpu) {
        super(id, nome, vpu);
    }

    public Commodity(String id, String nome, double vpu, String pais) {
        super(id, nome, vpu);
        this.pais = pais;
    }

    public String getPais(){
        return this.pais;
    }

    public String toString() {
        String s = super.toString();
        return s + "País: " + this.pais;
    }

    public double getValorPorUnidadeMaisRecente() {
        return this.getMercado().getCotacaoCommodity(this.getId());
    }
}