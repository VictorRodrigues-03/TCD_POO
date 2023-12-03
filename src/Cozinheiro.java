import java.util.ArrayList;

public class Cozinheiro extends Funcionario{
    ArrayList<PratoPrincipal> pratos = new ArrayList<PratoPrincipal>();

    public void adicionarPratos(PratoPrincipal prato){
        pratos.add(prato);
    }

    public Cozinheiro(PratoPrincipal prato,String nome, String cpf){
        super(nome, cpf);
        adicionarPratos(prato);
    }
}
