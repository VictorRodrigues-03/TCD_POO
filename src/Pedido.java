import java.util.ArrayList;

public class Pedido {
    private String dataPedido;
    private String horaPedido;
    private String horaPagamento;
    private double valorTotal;
    private String formaPagamento;
    private ArrayList<Item> itens;
    private Garcon atendenteG;
    private Cozinheiro atendenteC;

    public Pedido(Cozinheiro atendenteC, Garcon atendenteG, String dataPedido, String horaPedido){
        this.atendenteC = atendenteC;
        this.atendenteG = atendenteG;
        this.dataPedido = dataPedido;
        this.horaPedido = horaPedido;
    }
}
