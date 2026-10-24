package mrbet;

import java.util.Objects;

/**
 * Representa um time de futebol com identificador, nome e mascote.
 * 
 * @author Mikael Renan de Oliveira
 * 
 */
public class Time {
    private String idTime;
    private String nome;
    private String mascote;

    /**
     * Constrói um time.
     *
     * @param idTime  identificador único do time.
     * @param nome    nome do time.
     * @param mascote mascote do time.
     */
    public Time(String idTime, String nome, String mascote) {
        this.idTime = idTime;
        this.nome = nome;
        this.mascote = mascote;
    }

    /**
     * @return identificador do time.
     */
    public String getIdTime() {
        return idTime;
    }

    /**
     * @return nome do time.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return mascote do time.
     */
    public String getMascote() {
        return mascote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;
        Time time = (Time) o;
        return Objects.equals(idTime, time.idTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTime);
    }

    /**
     * Representação textual do time.
     * Exemplo: [250_PB] Nacional de Patos / Canario
     */
    @Override
    public String toString() {
        return "[" + idTime + "] " + nome + " / " + mascote;
    }
}
