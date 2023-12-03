import java.util.Objects;

public class Bebida extends Produto{
    private int tamanho;
    private String tipoE;

    public Bebida(String nome, double precoC, String codigo, int tamanho, String tipoE){
        super(nome, precoC, codigo);
        this.tamanho = tamanho;
        this.tipoE = tipoE;

    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bebida bebida = (Bebida) o;

        return Objects.equals(this.nome,bebida.nome) && Objects.equals(this.codigo,bebida.codigo) && Objects.equals(this.tamanho,bebida.tamanho);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nome,codigo,tamanho);
    }
}
