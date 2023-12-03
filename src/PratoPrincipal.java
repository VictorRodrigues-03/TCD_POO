import java.util.ArrayList;

public class PratoPrincipal extends Produto{
    ArrayList<Ingrediente> ingredientes;
    protected String descricaoC;
    protected double tempoP;

    public PratoPrincipal(String nome, double precoC, String codigo,double tempoP){
        super(nome,precoC,codigo);
        this.tempoP = tempoP;
    }

    public void adicionarIngrediente(Ingrediente ad){
        ingredientes.add(ad);
    }

    public double fazerPrato(){
        for (Ingrediente el : ingredientes){
            el.diminuirQ();
        }
        return precoV;
    }

}
