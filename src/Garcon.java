public class Garcon extends Funcionario{
    private String diaFolga;

    public Garcon(){

    }

    public Garcon(String nome, String cpf,double salarioBase){
        super(nome,cpf);
        this.salario = salarioBase;
    }

    public double calcSalario(boolean bonus){
        if (bonus){
            return salario+500;
        }
        return salario;
    }

}
