import java.util.ArrayList;

public class Cozinheiro extends Funcionario{
    ArrayList<PratoPrincipal> pratos = new ArrayList<PratoPrincipal>();

    private int quantPrato;
    private int quantSobremesa;

    public Cozinheiro(){

    }

    public void fezPrato(PratoPrincipal pedido){
        if (pedido instanceof Sobremesa){
            pedido.fazerPrato();
            quantSobremesa++;
            return;
        }
        pedido.fazerPrato();
        quantPrato++;
    }

    public void adicionarPratos(PratoPrincipal prato){
        pratos.add(prato);
    }

    public Cozinheiro(PratoPrincipal prato,String nome, String cpf){
        super(nome, cpf);
        adicionarPratos(prato);
        quantSobremesa = 0;
        quantPrato = 0;
    }

    public void getPratos(){
        int i = 0;
        for (PratoPrincipal element : pratos){
            System.out.println(i +" - "+element.getNome());
        }
        return;
    }

    public double calcSalario(){
        quantSobremesa = 0;
        quantPrato = 0;
        return (quantPrato*15)+(quantSobremesa*8);
    }

}
