package mrbet;

import easyaccept.EasyAccept;

/**
 * Fachada do sistema MrBet que serve como interface para os testes de aceitação.
 * Encapsula todas as operações do sistema e as expõe para o framework EasyAccept.
 * 
 * @author Monitores/LP2
 * 
 */
public class MrBetFachada {
    private MrBetController controller;
    
    /**
     * Construtor da fachada MrBet.
     * Inicializa o controlador do sistema.
     */
    public MrBetFachada() {
        this.controller = new MrBetController();
    }
    
    /**
     * Inclui um novo time no sistema.
     * 
     * @param codigo Código do time
     * @param nome Nome do time
     * @param mascote Mascote do time
     * @return Mensagem de resultado da operação
     */
    public String incluirTime(String codigo, String nome, String mascote) {
        return controller.incluirTime(codigo, nome, mascote);
    }
    
    /**
     * Recupera informações de um time.
     * 
     * @param codigo Código do time
     * @return Informações do time ou mensagem de erro
     */
    public String recuperarTime(String codigo) {
        return controller.recuperarTime(codigo);
    }
    
    /**
     * Adiciona um novo campeonato.
     * 
     * @param nome Nome do campeonato
     * @param participantes Capacidade do campeonato
     * @return Mensagem de resultado da operação
     */
    public String incluirCampeonato(String nome, int participantes) {
        return controller.incluirCampeonato(nome, participantes);
    }
    
    /**
     * Inclui um time em um campeonato.
     * 
     * @param codTime Código do time
     * @param nomeCamp Nome do campeonato
     * @return Mensagem de resultado da operação
     */
    public String incluirTimeCampeonato(String codTime, String nomeCamp) {
        return controller.incluirTimeCampeonato(codTime, nomeCamp);
    }
    
    /**
     * Verifica se um time está em um campeonato.
     * 
     * @param codTime Código do time
     * @param nomeCamp Nome do campeonato
     * @return Mensagem indicando a presença do time
     */
    public String verificaTimeCampeonato(String codTime, String nomeCamp) {
        return controller.verificaTimeCampeonato(codTime, nomeCamp);
    }
    
    /**
     * Lista campeonatos de um time.
     * 
     * @param codigo Código do time
     * @return Lista de campeonatos ou mensagem de erro
     */
    public String campsParticipando(String codigo) {
        return controller.campsParticipando(codigo);
    }
    
    /**
     * Registra uma aposta.
     * 
     * @param codTime Código do time
     * @param nomeCamp Nome do campeonato
     * @param colocacao Colocação prevista
     * @param valor Valor da aposta
     * @return Mensagem de resultado da operação
     */
    public String tentarSorte(String codTime, String nomeCamp, int colocacao, double valor) {
        return controller.tentarSorte(codTime, nomeCamp, colocacao, valor);
    }
    
    /**
     * Retorna status das apostas.
     * 
     * @return Lista de apostas ou mensagem de ausência
     */
    public String statusAposta() {
        return controller.statusAposta();
    }
    
    /**
     * Método principal para execução dos testes de aceitação.
     * 
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        args = new String[] {
            "mrbet.MrBetFachada",
            "testes_aceitacao/use_case_1.txt",
            "testes_aceitacao/use_case_2.txt", 
            "testes_aceitacao/use_case_3.txt",
            "testes_aceitacao/use_case_4.txt",
            "testes_aceitacao/use_case_5.txt",
            "testes_aceitacao/use_case_6.txt",
            "testes_aceitacao/use_case_7.txt"
        };
        EasyAccept.main(args);
    }
}