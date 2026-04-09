import java.rmi.registry.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PortoClient {
    private static IServico stub2;
    private static Scanner scanner;

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        scanner = new Scanner(System.in);

        try {
            // Obtém uma referência para o registro do RMI
            Registry registry = LocateRegistry.getRegistry(host, 6655);

            // Obtém a stub do servidor
            stub2 = (IServico) registry.lookup("Porto");

            // Menu interativo
            exibirMenuPrincipal();

        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao servidor!");
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void exibirMenuPrincipal() {
        int opcao;

        do {
            System.out.println("\n========== SISTEMA DE GERENCIAMENTO DE PORTO ==========");
            System.out.println("1. Gerenciar Navios");
            System.out.println("2. Gerenciar Cargas");
            System.out.println("3. Gerenciar Embarques"); // Nova opção adicionada
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    menuNavios();
                    break;
                case 2:
                    menuCargas();
                    break;
                case 3:
                    menuEmbarques(); // Chamada do novo menu
                    break;
                case 4:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuNavios() {
        int opcao;

        do {
            System.out.println("\n========== GERENCIAMENTO DE NAVIOS ==========");
            System.out.println("1. Cadastrar Navio");
            System.out.println("2. Listar Navios");
            System.out.println("3. Remover Navio");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    cadastrarNavio();
                    break;
                case 2:
                    listarNavios();
                    break;
                case 3:
                    removerNavio();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuCargas() {
        int opcao;

        do {
            System.out.println("\n========== GERENCIAMENTO DE CARGAS ==========");
            System.out.println("1. Cadastrar Carga");
            System.out.println("2. Listar Cargas");
            System.out.println("3. Remover Carga");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    cadastrarCarga();
                    break;
                case 2:
                    listarCargas();
                    break;
                case 3:
                    removerCarga();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    // NOVO MENU: Gerenciamento de Embarques
    private static void menuEmbarques() {
        int opcao;

        do {
            System.out.println("\n========== GERENCIAMENTO DE EMBARQUES ==========");
            System.out.println("1. Embarcar Carga");
            System.out.println("2. Relatório de Embarques");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    embarcarCarga();
                    break;
                case 2:
                    listarEmbarques();
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);
    }

    private static void cadastrarNavio() {
        try {
            System.out.print("\nDigite o nome do navio: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a capacidade do navio (toneladas): ");
            int capacidade = lerInt();

            Integer resultado = stub2.cadastrar_navio(nome, capacidade);
            System.out.println("✓ Navio cadastrado com sucesso! ID: " + resultado);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao cadastrar navio: " + ex.getMessage());
        }
    }

    private static void listarNavios() {
        try {
            String relatorio = stub2.relatorio_navio();
            System.out.println("\n========== RELATÓRIO DE NAVIOS ==========");
            System.out.println(relatorio);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao listar navios: " + ex.getMessage());
        }
    }

    private static void removerNavio() {
        try {
            System.out.print("\nDigite o ID do navio a remover: ");
            int id = lerInt();

            stub2.remover_navio(id);
            System.out.println("✓ Navio removido com sucesso!");
        } catch (Exception ex) {
            System.out.println("✗ Erro ao remover navio: " + ex.getMessage());
        }
    }

    private static void cadastrarCarga() {
        try {
            System.out.print("\nDigite o nome da carga: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o peso da carga (toneladas): ");
            int peso = lerInt();

            Integer resultado = stub2.cadastrar_carga(nome, peso);
            System.out.println("✓ Carga cadastrada com sucesso! ID: " + resultado);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao cadastrar carga: " + ex.getMessage());
        }
    }

    private static void listarCargas() {
        try {
            String relatorio = stub2.relatorio_carga();
            System.out.println("\n========== RELATÓRIO DE CARGAS ==========");
            System.out.println(relatorio);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao listar cargas: " + ex.getMessage());
        }
    }

    private static void removerCarga() {
        try {
            System.out.print("\nDigite o ID da carga a remover: ");
            int id = lerInt();

            stub2.remover_carga(id);
            System.out.println("✓ Carga removida com sucesso!");
        } catch (Exception ex) {
            System.out.println("✗ Erro ao remover carga: " + ex.getMessage());
        }
    }

    // NOVOS MÉTODOS: Consumindo funções de embarque via RMI
    private static void embarcarCarga() {
        try {
            System.out.print("\nDigite a descrição do embarque: ");
            String descricao = scanner.nextLine();

            double valor = stub2.embarcar(descricao);
            System.out.println("✓ Embarque realizado com sucesso! Valor/Retorno: " + valor);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao realizar embarque: " + ex.getMessage());
        }
    }

    private static void listarEmbarques() {
        try {
            String relatorio = stub2.relatorio_embarque();
            System.out.println("\n========== RELATÓRIO DE EMBARQUES ==========");
            System.out.println(relatorio);
        } catch (Exception ex) {
            System.out.println("✗ Erro ao listar embarques: " + ex.getMessage());
        }
    }

    private static int lerInt() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar o buffer
            System.out.print("Entrada inválida! Digite um número: ");
            return lerInt();
        }
    }
}