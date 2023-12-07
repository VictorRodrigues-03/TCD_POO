public class Garcon extends Funcionario{
    private double salarioBase;
    private String diaFolga;

    public Garcon(){

    }

    public Garcon(String nome, String cpf,double salarioBase){
        super(nome,cpf);
        this.salarioBase = salarioBase;
    }

    public double calculaSalario(boolean bonus){
        if (bonus){
            return salarioBase+500;
        }
        return salarioBase;
    }

}
