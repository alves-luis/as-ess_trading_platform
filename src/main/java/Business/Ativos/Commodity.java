package Business.Ativos;

public class Commodity extends Ativo {

    private String pais;

    public Commodity(String id, String nome, double vpu) {
        super(id, nome, vpu);
    }

    public Commodity(String id, String nome, double vpu, String pais) {
        super(id, nome, vpu);
        this.pais = pais;
    }

    public String toString() {
        String s = super.toString();
        return s + "\nPaís: " + this.pais;
    }

    @Override
    public void run() {

    }
}