import java.io.Serializable;
import java.util.Objects;

public class Item implements Quantitativo, Serializable {
    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void aumentarQ(){
        quantidade++;
    }

    public void aumentarQ(int quant){
        quantidade += quant;
    }

    public void diminuirQ(){
        quantidade--;
    }

    public double getValor(){
        return produto.precoV*((double) quantidade);
    }

    public void getItem(){
        System.out.println(produto.getNome() +" - "+quantidade+" - "+getValor());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return Objects.equals(this.produto,item.produto);
    }

    @Override
    public int hashCode(){
        return Objects.hash(produto);
    }
}
