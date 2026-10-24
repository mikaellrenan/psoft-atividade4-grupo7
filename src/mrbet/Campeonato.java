package mrbet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representa um campeonato que pode receber times como participantes.
 * Controla a quantidade máxima de times e os times já cadastrados.
 * 
 * @author Mikael Renan de Oliveira
 * 
 */
public class Campeonato {
    private String nomeCampeonato;
    private int maxParticipants;
    private Set<Time> participantes;

    /**
     * Constrói um campeonato.
     *
     * @param nomeCampeonato  nome do campeonato.
     * @param maxParticipants número máximo de participantes.
     */
    public Campeonato(String nomeCampeonato, int maxParticipants) {
        this.nomeCampeonato = nomeCampeonato;
        this.maxParticipants = maxParticipants;
        this.participantes = new HashSet<>();
    }

    /**
     * @return o nome do campeonato.
     */
    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    /**
     * @return a quantidade atual de participantes.
     */
    public int getCurrentParticipants() {
        return participantes.size();
    }

    /**
     * @return o limite máximo de participantes.
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Adiciona um time ao campeonato, se ainda houver vagas.
     *
     * @param time o time a ser adicionado.
     * @return true se conseguiu adicionar, false caso contrário.
     */
    public boolean addTeam(Time time) {
        if (participantes.size() < maxParticipants) {
            return participantes.add(time);
        }
        return false;
    }

    /**
     * Verifica se o campeonato contém determinado time.
     *
     * @param time o time a verificar.
     * @return true se o time participa, false caso contrário.
     */
    public boolean hasTeam(Time time) {
        return participantes.contains(time);
    }

    /**
     * @return conjunto de times participantes.
     */
    public Set<Time> getTeams() {
        return new HashSet<>(participantes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campeonato)) return false;
        Campeonato that = (Campeonato) o;
        return Objects.equals(nomeCampeonato, that.nomeCampeonato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCampeonato);
    }

    /**
     * Representação textual do campeonato.
     * Exemplo: Campeonato Brasileiro - 3/20
     */
    @Override
    public String toString() {
        return nomeCampeonato + " - " + participantes.size() + "/" + maxParticipants;
    }
}
