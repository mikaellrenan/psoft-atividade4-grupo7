package mrbet;

import java.util.*;

/**
 * Controlador principal do sistema MrBet.
 * Gerencia os times, campeonatos e apostas, oferecendo os m�todos de manipula��o.
 * 
 * @author Mikael Renan de Oliveira
 * 
 */

/**
 *  CODE SMELL IDENTIFICADO: DIVERGENT CHANGE
 *  A classe possui múltiplos motivos para ser modificada, pois manipula 
 *  diretamente o ciclo de vida e as regras de negócio de três domínios 
 *  distintos (Times, Campeonatos e Apostas). Esse comportamento
 *  é conhecido por classe GOD CLASS.
 *
 *  SOLUÇÃO PARA DIVERGENT CHANGE
 *  EXTRACT CLASS ou extração de classes, devemos dividir esta classe para que 
 *  suas manipulações sejam limitadas a dominios individuais deixando a  regra
 *  mais clara e reduzindo o acoplamento e aumentando a coesão. Criação de nova classe TimesController,
 *  CampeonatoController e ApostasController.
 * 
*/
public class MrBetController {
    private Map<String, Time> times;
    private Map<String, Campeonato> campeonatos;
    private List<Aposta> apostas;

    /**
     * Constr�i o controlador do sistema MrBet.
     */
    public MrBetController() {
        this.times = new HashMap<>();
        this.campeonatos = new HashMap<>();
        this.apostas = new ArrayList<>();
    }

    /**
     * Inclui um time no sistema.
     *
     * @param id      identificador do time.
     * @param nome    nome do time.
     * @param mascote mascote do time.
     * @return mensagem de sucesso ou erro.
     */
    public String incluirTime(String id, String nome, String mascote) {
        if (id == null || id.isBlank()) return "Nao pode adicionar o vazio";
        if (times.containsKey(id)) return "TIME JA EXISTE!";
        times.put(id, new Time(id, nome, mascote));
        return "INCLUSAO REALIZADA!";
    }

    /**
     * Recupera a representa��o de um time.
     *
     * @param id identificador do time.
     * @return string do time ou mensagem de erro.
     */
    public String recuperarTime(String id) {
        if (!times.containsKey(id)) return "O TIME NAO EXISTE!";
        return times.get(id).toString();
    }

    /**
     * Inclui um campeonato no sistema.
     *
     * @param nome           nome do campeonato.
     * @param maxParticipants limite de participantes.
     * @return mensagem de sucesso ou erro.
     */
    public String incluirCampeonato(String nome, int maxParticipants) {
        if (nome == null || nome.isBlank()) return "Nao pode adicionar o vazio";
        if (campeonatos.containsKey(nome)) return "CAMPEONATO JA EXISTE!";
        campeonatos.put(nome, new Campeonato(nome, maxParticipants));
        return "CAMPEONATO ADICIONADO!";
    }

    /**
     * Inclui um time em um campeonato.
     *
     * @param idTime   identificador do time.
     * @param nomeCamp nome do campeonato.
     * @return mensagem de resultado da opera��o.
     */
    public String incluirTimeCampeonato(String idTime, String nomeCamp) {
        if (!times.containsKey(idTime)) return "O TIME NAO EXISTE!";
        if (!campeonatos.containsKey(nomeCamp)) return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatos.get(nomeCamp);
        Time time = times.get(idTime);

        if (camp.hasTeam(time)) return "O TIME JA FOI INCLUIDO ANTERIORMENTE!";
        if (camp.getCurrentParticipants() >= camp.getMaxParticipants())
            return "TODOS OS TIMES DESSE CAMPEONATO JA FORAM INCLUIDOS!";

        camp.addTeam(time);
        return "TIME INCLUIDO NO CAMPEONATO!";
    }

    /**
     * Verifica se um time participa de um campeonato.
     *
     * @param idTime   id do time.
     * @param nomeCamp nome do campeonato.
     * @return mensagem de verifica��o.
     */
    public String verificaTimeCampeonato(String idTime, String nomeCamp) {
        if (!times.containsKey(idTime)) return "O TIME NAO EXISTE!";
        if (!campeonatos.containsKey(nomeCamp)) return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatos.get(nomeCamp);
        Time time = times.get(idTime);

        return camp.hasTeam(time) ? "O TIME ESTA NO CAMPEONATO!" : "O TIME NAO ESTA NO CAMPEONATO!";
    }

    /**
     * Lista campeonatos em que um time participa.
     *
     * @param idTime id do time.
     * @return string com os campeonatos ou mensagem de erro.
     */
    public String campsParticipando(String idTime) {
        if (!times.containsKey(idTime)) return "O TIME NAO EXISTE!";

        Time time = times.get(idTime);
        List<String> camps = new ArrayList<>();

        for (Campeonato camp : campeonatos.values()) {
            if (camp.hasTeam(time)) camps.add(camp.toString());
        }

        if (camps.isEmpty()) return "O TIME NAO ESTA EM NENHUM CAMPEONATO!";

        return "Campeonatos do " + time.getNome() + ":\n" + String.join("\n", camps);
    }

    /**
     * Registra uma aposta em um time dentro de um campeonato.
     *
     * @param idTime   id do time.
     * @param nomeCamp nome do campeonato.
     * @param colocacao coloca��o prevista.
     * @param valor     valor da aposta.
     * @return mensagem de resultado.
     */
    public String tentarSorte(String idTime, String nomeCamp, int colocacao, double valor) {
        if (!times.containsKey(idTime)) return "O TIME NAO EXISTE!";
        if (!campeonatos.containsKey(nomeCamp)) return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatos.get(nomeCamp);
        Time time = times.get(idTime);

        if (!camp.hasTeam(time)) return "O TIME NAO ESTA NO CAMPEONATO!";
        if (colocacao < 1 || colocacao > camp.getMaxParticipants()) return "APOSTA NAO REGISTRADA!";

        apostas.add(new Aposta(time, camp, String.valueOf(colocacao), String.valueOf(valor)));
        return "APOSTA REGISTRADA!";
    }

    /**
     * Exibe o status das apostas registradas.
     *
     * @return string listando as apostas ou mensagem de erro.
     */
    public String statusAposta() {
        if (apostas.isEmpty()) return "NAO TEM APOSTAS REGISTRADAS!";

        StringBuilder sb = new StringBuilder("Apostas: |\n");
        for (Aposta aposta : apostas) {
            sb.append(aposta.toString()).append(" | \n");
        }
        return sb.toString().trim();
    }
}
