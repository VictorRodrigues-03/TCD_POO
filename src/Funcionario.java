import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    protected String nome;
    protected String cpf;
    protected String rg;
    protected String estadoCivil;
    protected String endereco;
    protected String nCarteiraT;
    protected String dataAdmissao;
    protected double salario;

    public Funcionario(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public Funcionario(){

    }

    public String getNome(){
        String nova = this.nome;
        return nova;
    }


}
