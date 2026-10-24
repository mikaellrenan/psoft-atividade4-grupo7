package mrbet;

import java.util.Objects;

/**
 * Representa uma aposta realizada em um determinado campeonato para um time específico.
 * Cada aposta guarda as informações do time, campeonato, colocação prevista e valor da aposta.
 * 
 * @author Mikael Renan de Oliveira
 * 
 */
public class Aposta {
    private Time time;
    private Campeonato campeonato;
    private String colocacao;
    private String valor;

    /**
     * Constrói uma aposta.
     *
     * @param time       o time associado à aposta.
     * @param campeonato o campeonato no qual a aposta é feita.
     * @param colocacao  a colocação prevista para o time.
     * @param valor      o valor da aposta.
     */
    public Aposta(Time time, Campeonato campeonato, String colocacao, String valor) {
        this.time = time;
        this.campeonato = campeonato;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    /**
     * @return o time da aposta.
     */
    public Time getTime() {
        return time;
    }

    /**
     * @return a colocação prevista na aposta.
     */
    public String getColocacao() {
        return colocacao;
    }

    /**
     * @return o valor da aposta.
     */
    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aposta)) return false;
        Aposta aposta = (Aposta) o;
        return Objects.equals(time, aposta.time)
                && Objects.equals(campeonato, aposta.campeonato)
                && Objects.equals(colocacao, aposta.colocacao)
                && Objects.equals(valor, aposta.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, campeonato, colocacao, valor);
    }

    /**
     * Representação textual da aposta.
     * Exemplo: [001] Flamengo / Urubu | Brasileirao - 1/20 | R$1000.0
     */
    @Override
    public String toString() {
        return String.format("%s | %s - %s/%d | R$%s",
                time.toString(),
                campeonato.getNomeCampeonato(),
                colocacao,
                campeonato.getMaxParticipants(),
                valor);
    }
}
