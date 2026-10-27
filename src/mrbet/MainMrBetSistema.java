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
     * Cria o controlador do sistema, exibe o menu e trata todas as opções.
     *
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MrBetController sistema = new MrBetController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA MRBET ===");

        while (true) {
            System.out.println("\n=== MENU MRBET ===");
            System.out.println("(M) Minha inclusão de times");
            System.out.println("(R) Recuperar time");
            System.out.println("(.) Adicionar campeonato");
            System.out.println("(B) Bora incluir time em campeonato e Verificar");
            System.out.println("(E) Exibir campeonatos que o time participa");
            System.out.println("(T) Tentar a sorte e status");
            System.out.println("(!) Já pode fechar o programa!");
            System.out.print("Opção> ");
            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "M": {
                    System.out.println("\n--- INCLUIR TIME ---");
                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Mascote: ");
                    String mascote = scanner.nextLine();

                    String resultado = sistema.incluirTime(codigo, nome, mascote);
                    System.out.println(resultado);
                    break;
                }
                case "R": {
                    System.out.println("\n--- RECUPERAR TIME ---");
                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();

                    String resultado = sistema.recuperarTime(codigo);
                    System.out.println(resultado);
                    break;
                }
                case ".": {
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
                    break;
                }
                case "B": {
                    System.out.println("\n--- OPERAÇÕES TIME/CAMPEONATO ---");
                    System.out.print("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
                    String opcao = scanner.nextLine().toUpperCase();

                    if (opcao.equals("I")) {
                        System.out.println("\n--- INCLUIR TIME EM CAMPEONATO ---");
                        System.out.print("Código do time: ");
                        String codTime = scanner.nextLine();
                        System.out.print("Nome do campeonato: ");
                        String nomeCamp = scanner.nextLine();

                        String resultado = sistema.incluirTimeCampeonato(codTime, nomeCamp);
                        System.out.println(resultado);
                    } else if (opcao.equals("V")) {
                        System.out.println("\n--- VERIFICAR TIME EM CAMPEONATO ---");
                        System.out.print("Código do time: ");
                        String codTime = scanner.nextLine();
                        System.out.print("Nome do campeonato: ");
                        String nomeCamp = scanner.nextLine();

                        String resultado = sistema.verificaTimeCampeonato(codTime, nomeCamp);
                        System.out.println(resultado);
                    } else {
                        System.out.println("OPÇÃO INVÁLIDA!");
                    }
                    break;
                }
                case "E": {
                    System.out.println("\n--- EXIBIR CAMPEONATOS DO TIME ---");
                    System.out.print("Código do time: ");
                    String codigo = scanner.nextLine();

                    String resultado = sistema.campsParticipando(codigo);
                    System.out.println(resultado);
                    break;
                }
                case "T": {
                    System.out.println("\n--- OPERAÇÕES DE APOSTAS ---");
                    System.out.print("(A) Apostar ou (S) Status das Apostas? ");
                    String opcao = scanner.nextLine().toUpperCase();

                    if (opcao.equals("A")) {
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
                    } else if (opcao.equals("S")) {
                        System.out.println("\n--- STATUS DAS APOSTAS ---");
                        String resultado = sistema.statusAposta();
                        System.out.println(resultado);
                    } else {
                        System.out.println("OPÇÃO INVÁLIDA!");
                    }
                    break;
                }
                case "!":
                    System.out.println("\n=== ENCERRANDO SISTEMA MRBET ===");
                    System.out.println("Por hoje é só, pessoal!");
                    scanner.close();
                    return;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
    }
}
