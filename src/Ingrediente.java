public class Ingrediente implements Quantitativo{
    private String nome;
    private int quantidade;

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

}
