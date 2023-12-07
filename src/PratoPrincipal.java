import java.util.ArrayList;

public class PratoPrincipal extends Produto{
    protected ArrayList<Ingrediente> ingredientes;
    protected String descricaoC;
    protected double tempoP;

    public PratoPrincipal(String nome, double precoC, String codigo,double tempoP){
        super(nome,precoC,codigo);
        this.tempoP = tempoP;
        ingredientes = new ArrayList<Ingrediente>();
    }

    public void adicionarIngrediente(Ingrediente ad){
        ingredientes.add(ad);
    }

    public void getIngredientes(){
        for (Ingrediente element : ingredientes){
            System.out.println(element.getNome());
        }
    }

    public void fazerPrato(){
        for (Ingrediente el : ingredientes){
            el.diminuirQ();
        }
        return;
    }

}
