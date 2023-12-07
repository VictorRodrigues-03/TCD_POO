import java.io.Serializable;

public class Ingrediente implements Quantitativo, Serializable {
    private String nome;
    private int quantidade;

    public Ingrediente(){

    }

    public Ingrediente(String nome, int quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public void aumentarQ(){
        quantidade++;
    }

    public void diminuirQ(){
        quantidade--;
    }

    public String getNome(){
        String retorno = this.nome;
        return retorno;
    }

    public int getQuantidade(){
        int quant = quantidade;
        return quant;
    }

}
