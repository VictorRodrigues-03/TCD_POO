import java.util.Objects;

public abstract class Produto {
    protected String nome;
    protected String codigo;
    protected double precoV;
    protected double precoC;

    public Produto(String nome, double precoC, String codigo){
        this.nome = nome;
        this.precoC = precoC;
        precoV = precoC*1.5;
        this.codigo = codigo;

    }

    public String getNome(){
        String copia = this.nome;
        return copia;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return Objects.equals(this.nome,produto.nome) && Objects.equals(this.codigo,produto.codigo);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nome,codigo);
    }
}
