package mrbet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CampeonatoTeste {
    private Campeonato campeonato;
    private Time time1;
    private Time time2;

    @BeforeEach
    void setUp() {
        campeonato = new Campeonato("Campeonato Brasileiro", 2);
        time1 = new Time("SCCP_1910", "Corinthians", "Gaviao");
        time2 = new Time("CVG_B", "Vasco", "Pirata");
    }

    @Test
    void testGetNomeCampeonato() {
        assertEquals("Campeonato Brasileiro", campeonato.getNomeCampeonato());
    }

    @Test
    void testGetCurrentParticipants() {
        assertEquals(0, campeonato.getCurrentParticipants());
        campeonato.addTeam(time1);
        assertEquals(1, campeonato.getCurrentParticipants());
    }

    @Test
    void testGetMaxParticipants() {
        assertEquals(2, campeonato.getMaxParticipants());
    }

    @Test
    void testAddTeam() {
        assertTrue(campeonato.addTeam(time1));
        assertTrue(campeonato.addTeam(time2));
        Time time3 = new Time("003", "Time Extra", "Mascote");
        assertFalse(campeonato.addTeam(time3));
    }

    @Test
    void testHasTeam() {
        campeonato.addTeam(time1);
        assertTrue(campeonato.hasTeam(time1));
        assertFalse(campeonato.hasTeam(time2));
    }

    @Test
    void testGetTeams() {
        campeonato.addTeam(time1);
        campeonato.addTeam(time2);

        Set<Time> expectedTeams = new HashSet<>();
        expectedTeams.add(time1);
        expectedTeams.add(time2);

        assertEquals(expectedTeams, campeonato.getTeams());
    }

    @Test
    void testEqualsAndHashCode() {
        Campeonato campeonato2 = new Campeonato("Campeonato Brasileiro", 4);
        Campeonato campeonato3 = new Campeonato("Copa do Brasil", 4);

        assertEquals(campeonato, campeonato2);
        assertNotEquals(campeonato, campeonato3);

        assertEquals(campeonato.hashCode(), campeonato2.hashCode());
        assertNotEquals(campeonato.hashCode(), campeonato3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Campeonato Brasileiro - 0/2", campeonato.toString());
        campeonato.addTeam(time1);
        assertEquals("Campeonato Brasileiro - 1/2", campeonato.toString());
    }
}
