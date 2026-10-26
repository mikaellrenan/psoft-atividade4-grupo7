package mrbet;

import java.util.Objects;

public class Aposta {
    // Variável privada que armazena o objeto time da aposta
    private Time time;
    // Variável privada que armazena o campeonato associado
    private Campeonato campeonato;
    // Variável que guarda a colocação prevista
    private String colocacao;
    // Variável que guarda o valor em string
    private String valor;

    // Construtor padrão da classe Aposta para inicializar os atributos
    public Aposta(Time time, Campeonato campeonato, String colocacao, String valor) {
        // Atribui o time recebido
        this.time = time;
        // Atribui o campeonato recebido
        this.campeonato = campeonato;
        // Atribui a colocação
        this.colocacao = colocacao;
        // Atribui o valor
        this.valor = valor;
    }

    // Método get para retornar o time
    public Time getTime() {
        // Retorna a variável time
        return time;
    }

    // Método get para retornar a colocação
    public String getColocacao() {
        // Retorna a colocação
        return colocacao;
    }

    // Método get para retornar o valor
    public String getValor() {
        // Retorna o valor
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
        // Formata a string de exibição da aposta
        return String.format("%s | %s - %s/%d | R$%s",
                time.toString(),
                campeonato.getNomeCampeonato(),
                colocacao,
                campeonato.getMaxParticipants(),
                valor);
    }
}
