package mrbet;

import java.util.Objects;

public class Aposta {
    private Time time;
    private Campeonato campeonato;
    private String colocacao;
    private String valor;

    public Aposta(Time time, Campeonato campeonato, String colocacao, String valor) {
        this.time = time;
        this.campeonato = campeonato;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    public Time getTime() {
        return time;
    }

    public String getColocacao() {
        return colocacao;
    }

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
