import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {

    private boolean pago;
    private String nome;
    private String dataPedido;
    private String horaPedido;
    private String horaPagamento;
    private double valorTotal;
    private String formaPagamento;
    private ArrayList<Item> itens;
    private Garcon atendenteG;
    private Cozinheiro atendenteC;

    public Pedido(Cozinheiro atendenteC, Garcon atendenteG, String dataPedido, String horaPedido, String nome){
        this.nome = nome;
        this.atendenteC = atendenteC;
        this.atendenteG = atendenteG;
        this.dataPedido = dataPedido;
        this.horaPedido = horaPedido;
        itens = new ArrayList<Item>();
        this.formaPagamento = "";
        pago = false;
    }

    public void adicionarItem(Produto novoP, int quant){
        Item novoI = new Item(novoP,quant);
        if (itens.contains(novoI)){
            itens.get(itens.indexOf(novoI)).aumentarQ(quant);
            return;
        }
        itens.add(novoI);
        return;
    }

    public double getValorTotal(){
        valorTotal = 0;
        for (Item element : itens){
            valorTotal += element.getValor();
        }
        return valorTotal;
    }

    public double getValor(){
        return this.valorTotal;
    }

    public void getNota(){
        System.out.println("Restaurante Comp em Tudo");
        System.out.println("--------------------------");
        System.out.println("Informações do Pedido");
        System.out.println("Nome: "+nome);
        System.out.println("Data: "+dataPedido);
        System.out.println("Hora: "+horaPedido);
        System.out.println("Garçon: "+atendenteG.getNome());
        System.out.println("Cozinheiro: "+atendenteC.getNome());
        System.out.println("---------------------------------------");
        System.out.println("Item Pedidos: ");
        for (Item element : itens){
            element.getItem();
        }
        System.out.println("----------------------------------------");
        System.out.println("Valor Total: "+getValorTotal());
        if (this.pago){
            System.out.println("Horario Pagamento: "+horaPagamento);
            System.out.println("Forma Pagamento: "+formaPagamento);
            System.out.println("Pedido pago!!!");
        }
    }

    public String getNome(){
        String retorno = this.nome;
        return retorno;
    }

    public void getList(){
        for (Item element : itens){
            element.getItem();
        }
    }

    public boolean fazCozinheiro(PratoPrincipal prato){
        if (atendenteC.pratos.contains(prato)){
            return true;
        }
        System.out.println("Falso aqui");
        return false;
    }
    public boolean isPago(){
        return pago;
    }

    public boolean pagamento(double vP, String formaP, String horaP){
        if (vP == valorTotal){
            this.formaPagamento += formaP;
            pago = true;
            horaPagamento = horaP;
            return true;
        }
        this.formaPagamento += formaP+"+";
        valorTotal -= vP;
        return false;
    }

    public Cozinheiro getAtendenteC(){
        return this.atendenteC;
    }
}
