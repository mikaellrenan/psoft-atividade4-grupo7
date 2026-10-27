package mrbet;

import java.util.Scanner;

/**
 * Classe principal que fornece uma interface de linha de comando interativa
 * para o sistema MrBet. Permite aos usuários interagir com todas as funcionalidades
 * do sistema através de um menu textual.
 * 
 * @author Mikael Renan de Oliveira
 * 
 */
public class MainMrBetSistema {
    
    /**
     * Método principal que inicia a aplicação MrBet.
     * Cria o controlador do sistema e inicia o loop principal do menu.
     * 
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MrBetController sistema = new MrBetController();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA MRBET ===");
        
        // Loop principal da aplicação
        while (true) {
            printMenu();
            String option = scanner.nextLine().toUpperCase();
            
            // Processa a opção selecionada pelo usuário
            switch (option) {
                case "M":
                    incluirTime(sistema, scanner);
                    break;
                case "R":
                    recuperarTime(sistema, scanner);
                    break;
                case ".":
                    adicionarCampeonato(sistema, scanner);
                    break;
                case "B":
                    timeCampeonatoOperations(sistema, scanner);
                    break;
                case "E":
                    exibirCampeonatosTime(sistema, scanner);
                    break;
                case "T":
                    apostasOperations(sistema, scanner);
                    break;
                case "!":
                    sairPrograma(scanner);
                    return;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
    }
    
    /**
     * Exibe o menu principal do sistema MrBet com todas as opções disponíveis.
     * O menu segue o padrão especificado no documento de requisitos.
     */
    private static void printMenu() {
        System.out.println("\n=== MENU MRBET ===");
        System.out.println("(M) Minha inclusão de times");
        System.out.println("(R) Recuperar time");
        System.out.println("(.) Adicionar campeonato");
        System.out.println("(B) Bora incluir time em campeonato e Verificar");
        System.out.println("(E) Exibir campeonatos que o time participa");
        System.out.println("(T) Tentar a sorte e status");
        System.out.println("(!) Já pode fechar o programa!");
        System.out.print("Opção> ");
    }
    
    /**
     * Processa a inclusão de um novo time no sistema.
     * Solicita código, nome e mascote do time ao usuário.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void incluirTime(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- INCLUIR TIME ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Mascote: ");
        String mascote = scanner.nextLine();
        
        String resultado = sistema.incluirTime(codigo, nome, mascote);
        System.out.println(resultado);
    }
    
    /**
     * Processa a recuperação de informações de um time existente.
     * Solicita o código do time a ser recuperado.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void recuperarTime(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- RECUPERAR TIME ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        String resultado = sistema.recuperarTime(codigo);
        System.out.println(resultado);
    }
    
    /**
     * Processa a adição de um novo campeonato ao sistema.
     * Solicita nome e número de participantes do campeonato.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void adicionarCampeonato(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- ADICIONAR CAMPEONATO ---");
        System.out.print("Nome do campeonato: ");
        String nome = scanner.nextLine();
        System.out.print("Número de participantes: ");
        
        try {
            int participantes = Integer.parseInt(scanner.nextLine());
            String resultado = sistema.incluirCampeonato(nome, participantes);
            System.out.println(resultado);
        } catch (NumberFormatException e) {
            System.out.println("NÚMERO DE PARTICIPANTES INVÁLIDO!");
        }
    }
    
    /**
     * Gerencia as operações relacionadas a times e campeonatos.
     * Oferece subopções para incluir time em campeonato ou verificar participação.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void timeCampeonatoOperations(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- OPERAÇÕES TIME/CAMPEONATO ---");
        System.out.print("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
        String opcao = scanner.nextLine().toUpperCase();
        
        if (opcao.equals("I")) {
            incluirTimeCampeonato(sistema, scanner);
        } else if (opcao.equals("V")) {
            verificarTimeCampeonato(sistema, scanner);
        } else {
            System.out.println("OPÇÃO INVÁLIDA!");
        }
    }
    
    /**
     * Processa a inclusão de um time em um campeonato existente.
     * Solicita código do time e nome do campeonato.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void incluirTimeCampeonato(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- INCLUIR TIME EM CAMPEONATO ---");
        System.out.print("Código do time: ");
        String codTime = scanner.nextLine();
        System.out.print("Nome do campeonato: ");
        String nomeCamp = scanner.nextLine();
        
        String resultado = sistema.incluirTimeCampeonato(codTime, nomeCamp);
        System.out.println(resultado);
    }
    
    /**
     * Processa a verificação se um time participa de um campeonato.
     * Solicita código do time e nome do campeonato.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void verificarTimeCampeonato(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- VERIFICAR TIME EM CAMPEONATO ---");
        System.out.print("Código do time: ");
        String codTime = scanner.nextLine();
        System.out.print("Nome do campeonato: ");
        String nomeCamp = scanner.nextLine();
        
        String resultado = sistema.verificaTimeCampeonato(codTime, nomeCamp);
        System.out.println(resultado);
    }
    
    /**
     * Exibe todos os campeonatos em que um time participa.
     * Solicita o código do time.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void exibirCampeonatosTime(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- EXIBIR CAMPEONATOS DO TIME ---");
        System.out.print("Código do time: ");
        String codigo = scanner.nextLine();
        
        String resultado = sistema.campsParticipando(codigo);
        System.out.println(resultado);
    }
    
    /**
     * Gerencia as operações relacionadas a apostas.
     * Oferece subopções para fazer apostas ou consultar status.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void apostasOperations(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- OPERAÇÕES DE APOSTAS ---");
        System.out.print("(A) Apostar ou (S) Status das Apostas? ");
        String opcao = scanner.nextLine().toUpperCase();
        
        if (opcao.equals("A")) {
            fazerAposta(sistema, scanner);
        } else if (opcao.equals("S")) {
            exibirStatusApostas(sistema);
        } else {
            System.out.println("OPÇÃO INVÁLIDA!");
        }
    }
    
    /**
     * Processa o registro de uma nova aposta no sistema.
     * Solicita código do time, campeonato, colocação prevista e valor da aposta.
     * 
     * @param sistema Instância do controlador MrBet
     * @param scanner Scanner para entrada de dados do usuário
     */
    private static void fazerAposta(MrBetController sistema, Scanner scanner) {
        System.out.println("\n--- FAZER APOSTA ---");
        System.out.print("Código do time: ");
        String codTime = scanner.nextLine();
        System.out.print("Nome do campeonato: ");
        String nomeCamp = scanner.nextLine();
        System.out.print("Colocação prevista: ");
        
        try {
            int colocacao = Integer.parseInt(scanner.nextLine());
            System.out.print("Valor da aposta: R$");
            double valor = Double.parseDouble(scanner.nextLine());
            
            String resultado = sistema.tentarSorte(codTime, nomeCamp, colocacao, valor);
            System.out.println(resultado);
        } catch (NumberFormatException e) {
            System.out.println("VALOR INVÁLIDO!");
        }
    }
    
    /**
     * Exibe o status de todas as apostas registradas no sistema.
     * 
     * @param sistema Instância do controlador MrBet
     */
    private static void exibirStatusApostas(MrBetController sistema) {
        System.out.println("\n--- STATUS DAS APOSTAS ---");
        String resultado = sistema.statusAposta();
        System.out.println(resultado);
    }
    
    /**
     * Encerra a aplicação de forma controlada.
     * Exibe mensagem de despedida e fecha o scanner.
     * 
     * @param scanner Scanner a ser fechado
     */
    private static void sairPrograma(Scanner scanner) {
        System.out.println("\n=== ENCERRANDO SISTEMA MRBET ===");
        System.out.println("Por hoje é só, pessoal!");
        scanner.close();
    }
}
