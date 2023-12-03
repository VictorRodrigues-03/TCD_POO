public class Item implements Quantitativo{
    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void aumentarQ(){
        quantidade++;
    }

    public void diminuirQ(){
        quantidade--;
    }
}
