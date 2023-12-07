import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Restaurante implements Serializable{

    private double Saldo;
    private String diaPagamento;
    private int bonusP;
    public static void main(String[] args) {
        boolean program = true;
        Restaurante restaurante = carregarRestaurante();
        ArrayList<Funcionario> listaFuncionarios = carregarFuncionarios();
        ArrayList<Produto> listaProdutos = carregarProdutos();
        ArrayList<Pedido> listaPedidos = carregarPedidos();
        ArrayList<Ingrediente> listaIngredientes = carregarIngredientes();
        Scanner sc = new Scanner(System.in);
        while (program) {
            System.out.println("Bem Vindo ao restaurante Comp em Tudo");
            System.out.println("1 - Gerenciar funcionarios");
            System.out.println("2 - Gerenciar produtos");
            System.out.println("3 - Gerenciar Ingredientes");
            System.out.println("4 - Gerenciar Pedidos");
            System.out.println("5 - Gerenciar informações Restaurante");
            System.out.println("6 - Fechar programa");
            int op;
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    if (listaProdutos.isEmpty()){
                        System.out.println("A lista de produtos esta vazia");
                        break;
                    }
                    cadastrarFuncionario(sc,listaProdutos,listaFuncionarios);
                    break;
                case 2:
                    if (listaIngredientes.isEmpty()){
                        System.out.println("A lista de ingredientes esta vazia");
                        break;
                    }
                    cadastrarProdutos(sc,listaProdutos,listaIngredientes);
                    break;
                case 3:
                    gerenciarIngredientes(sc,listaIngredientes);
                    break;
                case 4:
                    if (listaProdutos.isEmpty() || listaFuncionarios.isEmpty() || listaIngredientes.isEmpty()){
                        System.out.println("A lista de ingredientes, ou produtos, ou funcionarios esta vazia");
                        break;
                    }
                    gerenciarPedidos(listaPedidos,listaFuncionarios,listaProdutos,restaurante);
                    break;
                case 5:
                    System.out.println("Escolha o dia de Pagamento do mês(Com os dois digitos): ");
                    String dia = sc.nextLine();
                    System.out.println("Digite a quantidade de Pedidos para ter Bonus");
                    int bonus = sc.nextInt();
                    sc.nextLine();
                    restaurante.setRestaurante(dia,bonus);
                    break;
                case 6:
                    program = false;
                    break;
                default:
                    break;
            }
        }
        restaurante.getContas(listaPedidos,listaFuncionarios);
        salvarIngredientes(listaIngredientes);
        salvarRestaurante(restaurante);
        salvarPedidos(listaPedidos);
        salvarProdutos(listaProdutos);
        salvarFuncionarios(listaFuncionarios);
        System.out.println("Obrigado pela preferencia");
        System.out.println("Ass: Comp em Tudo");
        System.out.println("Saldo: "+restaurante.getSaldo());
    }

    public static void cadastrarFuncionario(Scanner sc, ArrayList<Produto> produtos, ArrayList<Funcionario> funcionarios){
        int op;
        System.out.println("----Gerenciamento de funcinarios----");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Remover");
        System.out.println("3 - Adicionar um prato ao cozinheiro");
        op = sc.nextInt();
        sc.nextLine();
        switch (op){
            case 1:
                int op2;
                System.out.println("----Cadastrar novo funcinário----");
                System.out.println("1 - Cozinheiro");
                System.out.println("2 - Garcon");
                op2 = sc.nextInt();
                sc.nextLine();
                switch (op2){
                    case 1:
                        String nomeC = "";
                        System.out.println("Digite o nome do novo funcinario: ");
                        nomeC = sc.nextLine();
                        String cpfC = "";
                        do {
                            System.out.println("Digite o CPF do novo funcionario: ");
                            cpfC = sc.nextLine();
                        }while (!validarCPF(cpfC));
                        do {
                            System.out.println("Escolha um Prato ou sobremesa pra adicionar ao Cozinheiro: ");
                            op = escolheProdutos(produtos);
                            if (produtos.get(op) instanceof PratoPrincipal || produtos.get(op) instanceof Sobremesa){
                                break;
                            }
                        }while (true);
                        Cozinheiro novoC = new Cozinheiro((PratoPrincipal) produtos.get(op),nomeC,cpfC);
                        funcionarios.add(novoC);
                        System.out.println("Cozinheiro adicionado!!");
                        break;
                    case 2:
                        String nomeG = "";
                        System.out.println("Digite o nome do novo funcinario: ");
                        nomeG = sc.nextLine();
                        String cpfG = "";
                        do {
                            System.out.println("Digite o CPF do novo funcionario: ");
                            cpfG = sc.nextLine();
                        }while (!validarCPF(cpfG));
                        Garcon novoG = new Garcon(nomeG,cpfG,1200);
                        funcionarios.add(novoG);
                        System.out.println("Garcon adicionado!!");
                        break;
                    default:
                        System.out.println("Valor Invalido");
                        System.out.println("Tente novamente");
                        op = sc.nextInt();
                        sc.nextLine();
                }
                return;
            case 2:
                if (funcionarios.isEmpty()) return;
                percorreFuncinarios(funcionarios);
                do {
                    System.out.println("Escolha o funcinario a ser excluido: ");
                    op = sc.nextInt();
                    sc.nextLine();
                    if (op < funcionarios.size() && op >= 0){
                        break;
                    }
                }while (true);
                funcionarios.remove(op);
                System.out.println("Funcinario removido");
                percorreFuncinarios(funcionarios);
                return;
            case 3:
                if (funcionarios.isEmpty()) return;
                percorreFuncinarios(funcionarios);
                do {
                    System.out.println("Escolha um COZINHEIRO: ");
                    op = sc.nextInt();
                    sc.nextLine();
                    if (op < funcionarios.size() && op >= 0){
                        if (funcionarios.get(op) instanceof Cozinheiro){
                            Cozinheiro cozin = (Cozinheiro) funcionarios.get(op);
                            System.out.println("O cozinheiro "+cozin.getNome()+" faz os seguintes pratos: ");
                            cozin.getPratos();
                            System.out.println("----------------------------------------------");
                            break;
                        }
                    }
                }while (true);
                int Op2;
                do {
                    System.out.println("Escolha um PRATO PRINCIPAL ou uma SOBREMESA: ");
                    Op2 = escolheProdutos(produtos);
                    if (produtos.get(Op2) instanceof PratoPrincipal || produtos.get(Op2) instanceof Sobremesa){
                        break;
                    }
                }while (true);
                ((Cozinheiro) funcionarios.get(op)).adicionarPratos((PratoPrincipal) produtos.get(Op2));
                return;
            default:
                System.out.println("----Gerenciamento de funcinarios----");
                System.out.println("1 - Cadastrar");
                System.out.println("2 - Remover");
                System.out.println("3 - Adicionar um prato ao cozinheiro");
                op = sc.nextInt();
                sc.nextLine();
        }
    }

    public static void cadastrarProdutos(Scanner sc, ArrayList<Produto> produtos, ArrayList<Ingrediente> ingredientes){
        int op;
        System.out.println("----Gerenciamento de Produtos----");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Excluir produto");
        System.out.println("3 - Adicionar novo Ingrediente ao Prato");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                int op2;
                String nomeNovo = "";
                String codigoNovo = "";
                double precoCompra;
                System.out.println("Digite o nome do novo produto: ");
                nomeNovo = sc.nextLine();
                System.out.println("Digite o preço de compra do produto: ");
                precoCompra = sc.nextDouble();
                sc.nextLine();
                System.out.println("Digite o codigo do novo produto");
                codigoNovo = sc.nextLine();
                System.out.println("----Escolha o tipo de produto que deseja criar----");
                System.out.println("1 - Prato Principal");
                System.out.println("2 - Sobremesa");
                System.out.println("3 - Bebida");
                op2 = sc.nextInt();
                sc.nextLine();
                switch (op2) {
                    case 1:
                        double tempoPreparo;
                        int op3;
                        System.out.println("Digite o tempo de preparo do prato(em minutos):");
                        tempoPreparo = sc.nextDouble();
                        sc.nextLine();
                        PratoPrincipal novoPrato = new PratoPrincipal(nomeNovo, precoCompra, codigoNovo, tempoPreparo);
                        System.out.println("Escolha um ingrediente do prato: ");
                        op3 = escolheIngredientes(ingredientes);
                        novoPrato.adicionarIngrediente(ingredientes.get(op3));
                        if (!produtos.contains(novoPrato)) {
                            produtos.add(novoPrato);
                            System.out.println("O novo prato foi adicionado a lista de produtos");
                            return;
                        }
                        System.out.println("O prato já existe, ou seu nome e codigo já existem, tente de novo trocando-os");
                        return;
                    case 2:
                        int opS;
                        double tempoPreparoSobremesa;
                        double nCaloriasNovo;
                        System.out.println("Digite o tempo de preparo da sobremesa(em minutos): ");
                        tempoPreparoSobremesa = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Digite o numero de calorias da sobremesa(em gramas): ");
                        nCaloriasNovo = sc.nextDouble();
                        sc.nextLine();
                        Sobremesa novaSobremesa = new Sobremesa(nomeNovo, precoCompra, codigoNovo, tempoPreparoSobremesa, nCaloriasNovo);
                        System.out.println("Escolha um ingrediente da sobremesa: ");
                        opS = escolheIngredientes(ingredientes);
                        novaSobremesa.adicionarIngrediente(ingredientes.get(opS));
                        if (!produtos.contains(novaSobremesa)) {
                            produtos.add(novaSobremesa);
                            System.out.println("A nova sobremesa foi adicionada a lista de produtos");
                            return;
                        }
                        System.out.println("A sobremesa já existe, ou seu nome e codigo já existem, tente de novo trocando-os");
                        return;
                    case 3:
                        String novaEmbalagem = "";
                        int novoTamanho;
                        System.out.println("Digite o tipo da embalagem da nova bebida: ");
                        novaEmbalagem = sc.nextLine();
                        System.out.println("Digite o tamanho da  nova bebida(em ml): ");
                        novoTamanho = sc.nextInt();
                        sc.nextLine();
                        Bebida novaBebida = new Bebida(nomeNovo, precoCompra, codigoNovo, novoTamanho, novaEmbalagem);
                        if (!produtos.contains(novaBebida)) {
                            produtos.add(novaBebida);
                            System.out.println("A nova Bebida foi adicionada a lista de produtos");
                            return;
                        }
                        System.out.println("A Bebida já existe, ou seu nome, codigo e tamanho já existem, tente de novo trocando-os");
                        return;
                    default:
                        System.out.println("Digito incorreto");
                        System.out.println("----Escolha o tipo de produto que deseja criar----");
                        System.out.println("1 - Prato Principal");
                        System.out.println("2 - Sobremesa");
                        System.out.println("3 - Bebida");
                        op2 = sc.nextInt();
                        sc.nextLine();
                }
            case 2:
                int i;
                if (produtos.isEmpty()) return;
                i = escolheProdutos(produtos);
                produtos.remove(i);
                System.out.println("Produto removido com sucesso");
                return;
            case 3:
                if (produtos.isEmpty()) return;
                int prato;
                do {
                    System.out.println("Escolha um PRATO PRINCIPAL ou uma SOBREMESA: ");
                    prato = escolheProdutos(produtos);
                    if (produtos.get(prato) instanceof PratoPrincipal || produtos.get(prato) instanceof Sobremesa){
                        break;
                    }
                }while (true);
                int ingrediente;
                System.out.println("Escolha um ingrediente para adicionar ao prato: ");
                ingrediente = escolheIngredientes(ingredientes);
                ((PratoPrincipal) produtos.get(prato)).adicionarIngrediente(ingredientes.get(ingrediente));
                System.out.println("Prato : "+produtos.get(prato).getNome());
                System.out.println("Ingredientes: ");
                ((PratoPrincipal) produtos.get(prato)).getIngredientes();
                System.out.println("Adicionado com sucesso!!!");
                return;
            default:
                System.out.println("----Gerenciamento de Produtos----");
                System.out.println("1 - Cadastrar novo produto");
                System.out.println("2 - Excluir produto");
                System.out.println("3 - Adicionar novo Ingrediente ao Prato");
                op = sc.nextInt();
                sc.nextLine();
        }
    }

    public static void gerenciarIngredientes(Scanner sc,ArrayList<Ingrediente> listaIngredientes){
        System.out.println("----Menu de Ingredientes----");
        System.out.println("1 - Diminuir quantidade do ingrediente");
        System.out.println("2 - Aumentar quantidade do ingrediente");
        System.out.println("3 - Adicionar Ingrediente");
        System.out.println("4 - Remover Ingrediente");
        int op;
        int quant = 0;
        int indice;
        String nomeNovo = new String();
        op = sc.nextInt();
        sc.nextLine();
        switch (op){
            case 1:
                if (listaIngredientes.isEmpty()) return;
                System.out.println("Escolha o produto que deseja diminuir a quantidade");
                indice = escolheIngredientes(listaIngredientes);
                System.out.println("Digite a quantidade que deseja diminuir: ");
                quant = sc.nextInt();
                sc.nextLine();
                for (int i = 0;i < quant;i++){
                    listaIngredientes.get(indice).diminuirQ();
                }
                System.out.println("Quantidade reduzida com sucesso");
                return;
            case 2:
                if (listaIngredientes.isEmpty()) return;
                System.out.println("Escolha o produto que deseja aumentar a quantidade");
                indice = escolheIngredientes(listaIngredientes);
                System.out.println("Digite a quantidade que deseja aumentar: ");
                quant = sc.nextInt();
                sc.nextLine();
                for (int i = 0;i < quant;i++){
                    listaIngredientes.get(indice).aumentarQ();
                }
                System.out.println("Quantidade adicionada com sucesso");
                return;
            case 3:
                System.out.println("Digite o nome do ingrediente que deseja adicionar: ");
                nomeNovo = sc.nextLine();
                System.out.println("Digite a quantidade do novo Ingrediente(na medida do produto): ");
                quant = sc.nextInt();
                sc.nextLine();
                Ingrediente novoI = new Ingrediente(nomeNovo,quant);
                listaIngredientes.add(novoI);
                System.out.println("Novo ingrediente adicionado com sucesso");
                return;
            case 4:
                if (listaIngredientes.isEmpty()) return;
                System.out.println("Escolha o ingrediente que deseja remover");
                indice = escolheIngredientes(listaIngredientes);
                listaIngredientes.remove(indice);
                System.out.println("Removido com sucesso");
                return;
            default:
                System.out.println("----Menu de Ingredientes----");
                System.out.println("1 - Diminuir quantidade do ingrediente");
                System.out.println("2 - Aumentar quantidade do ingrediente");
                System.out.println("3 - Adicionar Ingrediente");
                System.out.println("4 - Remover Ingrediente");
                op = sc.nextInt();
                sc.nextLine();
        }

    }

    public static void iniciarPedido(ArrayList<Pedido> listaPedidos, ArrayList<Funcionario> listaFuncionarios){
        Scanner sc = new Scanner(System.in);
        String horarioP = obterHorarioAtual();
        String dataP = obterDataAtual();
        String nomeCliente;
        Garcon atendenteG;
        Cozinheiro atendenteC;
        System.out.println("Digite o nome do Cliente: ");
        nomeCliente = sc.nextLine();
        int op;
        percorreFuncinarios(listaFuncionarios);
        while (true){
            System.out.println("Escolha um GARÇON para servir a mesa: ");
            op = sc.nextInt();
            sc.nextLine();
            if (listaFuncionarios.get(op) instanceof Garcon ){
                atendenteG = (Garcon) listaFuncionarios.get(op);
                break;
            }
        }
        percorreFuncinarios(listaFuncionarios);
        while (true){
            System.out.println("Escolha um COZINHEIRO para servir a mesa: ");
            op = sc.nextInt();
            sc.nextLine();
            if (listaFuncionarios.get(op) instanceof Cozinheiro){
                atendenteC = (Cozinheiro) listaFuncionarios.get(op);
                break;
            }
        }
        Pedido novoP = new Pedido(atendenteC,atendenteG,dataP,horarioP,nomeCliente);
        listaPedidos.add(novoP);
        return;

    }

    public static void gerenciarPedidos(ArrayList<Pedido> listaPedidos, ArrayList<Funcionario> listaFuncionarios, ArrayList<Produto> listaProdutos, Restaurante res){
        Scanner sc = new Scanner(System.in);
        int op;
        int pedido;
        System.out.println("----Menu de Pedidos----");
        System.out.println("1 - Iniciar um novo Pedido");
        System.out.println("2 - Adicionar produtos a um pedido");
        System.out.println("3 - Finalizar um pedido");
        op = sc.nextInt();
        sc.nextLine();
        switch (op){
            case 1:
                iniciarPedido(listaPedidos,listaFuncionarios);
                return;
            case 2:
                if (listaPedidos.isEmpty()) return;
                int total = listaPedidos.size();
                int pagos = 0;
                for (Pedido element : listaPedidos){
                    if (element.isPago()){
                        pagos++;
                    }
                }
                if (pagos == total){
                    System.out.println("Não existe nenhum pedido em andamento");
                    return;
                }
                pedido = escolhePedidos(listaPedidos);
                int produto = escolheProdutos(listaProdutos);
                System.out.println("Quantos itens deseja do Produto escolhido: ");
                int quant = sc.nextInt();
                sc.nextLine();
                if (!(listaProdutos.get(produto) instanceof Bebida)){
                    if (listaPedidos.get(pedido).fazCozinheiro((PratoPrincipal) listaProdutos.get(produto))){
                        listaPedidos.get(pedido).adicionarItem(listaProdutos.get(produto),quant);
                        listaPedidos.get(pedido).getAtendenteC().fezPrato((PratoPrincipal) listaProdutos.get(produto));
                        System.out.println("Pedido realizado com Sucesso!!");
                        return;
                    }
                    System.out.println("O cozinheiro não faz o Prato pedido.");
                    return;
                }
                listaPedidos.get(pedido).adicionarItem(listaProdutos.get(produto),quant);
                System.out.println("Pedido realizado com Sucesso!!");
                return;
            case 3:
                if (listaPedidos.isEmpty()) return;
                pedido = escolhePedidos(listaPedidos);
                listaPedidos.get(pedido).getNota();
                while (true){
                    System.out.println("Qual a forma de pagamento? ");
                    String formaP = sc.nextLine();
                    System.out.println("Qual o valor pago?");
                    double vP = sc.nextDouble();
                    sc.nextLine();
                    if (listaPedidos.get(pedido).pagamento(vP,formaP,obterHorarioAtual())){
                        System.out.println("Pedidos pago com sucesso!!!");
                        res.lucro(listaPedidos.get(pedido).getValorTotal());
                        return;
                    }
                    System.out.println("Pague o resto do valor da conta");
                    System.out.println(listaPedidos.get(pedido).getValor());
                }
            default:
                System.out.println("----Menu de Pedidos----");
                System.out.println("1 - Iniciar um novo Pedido");
                System.out.println("2 - Adicionar produtos a um pedido");
                System.out.println("3 - Finalizar um pedido");
                op = sc.nextInt();
                sc.nextLine();
        }

    }

    public static boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma1 = 0;
        int soma2 = 0;

        // Calcula o primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }
        int resto1 = soma1 % 11;
        int digitoVerificador1 = (resto1 < 2) ? 0 : (11 - resto1);

        // Calcula o segundo dígito verificador
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }
        int resto2 = soma2 % 11;
        int digitoVerificador2 = (resto2 < 2) ? 0 : (11 - resto2);

        // Verifica se os dígitos verificadores calculados coincidem com os dígitos fornecidos
        return (digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10]);
    }

    public static int escolheProdutos(ArrayList<Produto> produtos){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        for (Produto element: produtos){
            System.out.println(i +" - "+element.getNome());
            System.out.println("Tipo: "+element.getClass().getSimpleName());
            System.out.println("----------------------------");
            i++;
        }
        do {
            System.out.println("Escolha o indice do Produto desejado: ");
            i = sc.nextInt();
            sc.nextLine();
            if (i < produtos.size() && i >= 0) {
                break;
            }
        } while (true);
        return i;
    }

    public static void percorreFuncinarios(ArrayList<Funcionario> funcinarios){
        int i = 0;
        for (Funcionario element: funcinarios){
            System.out.println(i +" - "+element.getNome());
            System.out.println("Tipo: "+element.getClass().getSimpleName());
            System.out.println("----------------------------");
            i++;
        }
    }

    public static int escolheIngredientes(ArrayList<Ingrediente> ingredientes){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        for (Ingrediente element : ingredientes){
            System.out.println(i +" - "+element.getNome());
            System.out.println("Tipo: "+element.getClass().getSimpleName());
            System.out.println("Quantidade: "+element.getQuantidade());
            System.out.println("----------------------------");
            i++;
        }
        do {
            System.out.println("Escolha o indice do Ingrediente desejado: ");
            i = sc.nextInt();
            sc.nextLine();
            if (i < ingredientes.size() && i >= 0) {
                break;
            }
        } while (true);
        return i;
    }

    public static int escolhePedidos(ArrayList<Pedido> listaPedidos){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        for (Pedido element : listaPedidos){
            if (!element.isPago()) {
                System.out.println(i + " - " + element.getNome());
                element.getList();
                System.out.println("----------------------------");
            }
            i++;
        }
        do {
            System.out.println("Escolha o indice do Pedido desejado: ");
            i = sc.nextInt();
            sc.nextLine();
            if (i < listaPedidos.size() && i >= 0 && !listaPedidos.get(i).isPago()) {
                break;
            }
        } while (true);
        return i;
    }

    // Função para obter a data atual
    public static String obterDataAtual() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return agora.format(formatoData);
    }

    // Função para obter o horário atual
    public static String obterHorarioAtual() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
        return agora.format(formatoHorario);
    }

    public boolean fimDoMes(String data) {
        if (data != null && diaPagamento != null && data.length() >= 2 && diaPagamento.length() >= 2) {
            return data.regionMatches(0, diaPagamento, 0, 2);
        }
        return false;
    }

    public void getContas(ArrayList<Pedido> listaPedidos, ArrayList<Funcionario> listaFuncionarios){
        if (fimDoMes(obterDataAtual())){
            if (listaPedidos.isEmpty()) return;
            int totalP = 0;
            System.out.println("DIA DE PAGAMENTO");
            for (Pedido element : listaPedidos){
                if (element.isPago()){
                    totalP++;
                }
            }
            if (totalP >= this.getBonusP()){
                for (Funcionario element : listaFuncionarios){
                    if (element instanceof Garcon){
                        Garcon garcon = (Garcon) element;
                        this.gasto(garcon.calcSalario(true));
                    }else if (element instanceof Cozinheiro){
                        Cozinheiro cozin = (Cozinheiro) element;
                        this.gasto(cozin.calcSalario());
                    }
                }
            }else {
                for (Funcionario element : listaFuncionarios){
                    if (element instanceof Garcon){
                        Garcon garcon = (Garcon) element;
                        this.gasto(garcon.calcSalario(false));
                    }else if (element instanceof Cozinheiro){
                        Cozinheiro cozin = (Cozinheiro) element;
                        this.gasto(cozin.calcSalario());
                    }
                }
            }
            listaPedidos.clear();
            return;
        }
        System.out.println("DATA: "+obterDataAtual());
        System.out.println("HORA: "+obterHorarioAtual());
        return;
    }

    private void lucro(double valor){
        this.Saldo += valor;
    }

    private void gasto(double valor){
        this.Saldo -= valor;
    }

    public void setRestaurante(String diaPagamento, int bonusP){
        this.diaPagamento = diaPagamento;
        this.bonusP = bonusP;
    }

    public int getBonusP(){
        return this.bonusP;
    }

    public double getSaldo(){
        return this.Saldo;
    }

    public Restaurante(){
        this.Saldo = 0;

    }

    private static void salvarFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("funcionarios.bin"))) {
            outputStream.writeObject(listaFuncionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Funcionario> carregarFuncionarios() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("funcionarios.bin"))) {
            return (ArrayList<Funcionario>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se ocorrer uma exceção (por exemplo, o arquivo não existe), retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    private static void salvarProdutos(ArrayList<Produto> listaProdutos) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("produtos.bin"))) {
            outputStream.writeObject(listaProdutos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Produto> carregarProdutos() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("produtos.bin"))) {
            return (ArrayList<Produto>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se ocorrer uma exceção (por exemplo, o arquivo não existe), retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    private static void salvarPedidos(ArrayList<Pedido> listaPedidos) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pedidos.bin"))) {
            outputStream.writeObject(listaPedidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Pedido> carregarPedidos() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pedidos.bin"))) {
            return (ArrayList<Pedido>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se ocorrer uma exceção (por exemplo, o arquivo não existe), retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    private static void salvarIngredientes(ArrayList<Ingrediente> listaIngredientes) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ingredientes.bin"))) {
            outputStream.writeObject(listaIngredientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Ingrediente> carregarIngredientes() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ingredientes.bin"))) {
            return (ArrayList<Ingrediente>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se ocorrer uma exceção (por exemplo, o arquivo não existe), retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    private static void salvarRestaurante(Restaurante restaurante) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("restaurante.bin"))) {
            outputStream.writeObject(restaurante);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Restaurante carregarRestaurante() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("restaurante.bin"))) {
            return (Restaurante) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Restaurante();
        }
    }

}