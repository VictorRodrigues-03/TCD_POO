import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    public static void main(String[] args) {
        boolean program = true;
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        ArrayList<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
        Scanner sc = new Scanner(System.in);
        while (program) {
            System.out.println("Bem Vindo ao restaurante Comp em Tudo");
            System.out.println("1 - Gerenciar funcionarios");
            System.out.println("2 - Gerenciar produtos");
            System.out.println("3 - Começar pedido");
            System.out.println("4 - Fechar programa");
            int op;
            op = sc.nextInt();
            switch (op) {
                case 1:
                    cadastrarFuncionario(sc,listaProdutos,listaFuncionarios);
                    break;
                case 2:
                    cadastrarProdutos(sc,listaProdutos);
                    break;
                case 3:
                    break;
                case 4:
                    program = false;
                    break;
                default:
                    System.out.println("Valor Invalido");
                    System.out.println("Tente novamente");
                    op = sc.nextInt();
            }
        }
        System.out.println("Obrigado pela preferencia");
        System.out.println("Ass: Comp em Tudo");
    }

    public static void cadastrarFuncionario(Scanner sc, ArrayList<Produto> produtos, ArrayList<Funcionario> funcionarios){
        int op;
        System.out.println("----Gerenciamento de funcinarios----");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Remover");
        op = sc.nextInt();
        sc.nextLine();
        if (!produtos.isEmpty() && op == 1){
            System.out.println("----Cadastrar novo funcinário----");
            System.out.println("1 - Cozinheiro");
            System.out.println("2 - Garcon");
            op = sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1:
                    String nomeC = "";
                    System.out.println("Digite o nome do novo funcinario: ");
                    nomeC = sc.nextLine();
                    String cpfC = "";
                    do {
                        System.out.println("Digite o CPF do novo funcionario: ");
                        cpfC = sc.nextLine();
                    }while (!validarCPF(cpfC));
                    percorreProdutos(produtos);
                    do {
                        System.out.println("Escolha um Prato ou sobremesa pra adicionar ao Cozinheiro: ");
                        op = sc.nextInt();
                        sc.nextLine();
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
        }
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

    }

    public static void cadastrarProdutos(Scanner sc, ArrayList<Produto> produtos){
        int op;
        System.out.println("----Gerenciamento de Produtos----");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Excluir produto");
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
                        System.out.println("Digite o tempo de preparo do prato");
                        tempoPreparo = sc.nextDouble();
                        sc.nextLine();
                        PratoPrincipal novoPrato = new PratoPrincipal(nomeNovo, precoCompra, codigoNovo, tempoPreparo);
                        if (!produtos.contains(novoPrato)) {
                            produtos.add(novoPrato);
                            System.out.println("O novo prato foi adicionado a lista de produtos");
                            return;
                        }
                        System.out.println("O prato já existe, ou seu nome e codigo já existem, tente de novo trocando-os");
                        return;
                    case 2:
                        double tempoPreparoSobremesa;
                        double nCaloriasNovo;
                        System.out.println("Digite o tempo de preparo da sobremesa: ");
                        tempoPreparoSobremesa = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Digite o numero de calorias da sobremesa: ");
                        nCaloriasNovo = sc.nextDouble();
                        sc.nextLine();
                        Sobremesa novaSobremesa = new Sobremesa(nomeNovo, precoCompra, codigoNovo, tempoPreparoSobremesa, nCaloriasNovo);
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
                        System.out.println("Digite o tamanho da  nova bebida: ");
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
                percorreProdutos(produtos);
                do {
                    System.out.println("Escolha o indice do produto a ser excluido: ");
                    i = sc.nextInt();
                    sc.nextLine();
                    if (op < produtos.size() && op >= 0) {
                        break;
                    }
                } while (true);
                produtos.remove(i);
                System.out.println("Produto removido com sucesso");
                return;
            default:
                System.out.println("----Gerenciamento de Produtos----");
                System.out.println("1 - Cadastrar novo produto");
                System.out.println("2 - Excluir produto");
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

    public static void percorreProdutos(ArrayList<Produto> produtos){
        int i = 0;
        for (Produto element: produtos){
            System.out.println(i +" - "+element.getNome());
            System.out.println("Tipo: "+element.getClass().getSimpleName());
            System.out.println("----------------------------");
            i++;
        }
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
}