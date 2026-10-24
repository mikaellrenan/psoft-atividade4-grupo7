package mrbet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApostaTeste {
    private Time time1;
    private Campeonato campeonato1;
    private Aposta aposta1;
    private Aposta aposta2;

    @BeforeEach
    void setUp() {
        time1 = new Time("001", "Clube de Regatas do Flamengo", "Urubu");
        campeonato1 = new Campeonato("Brasileirao", 20);
        aposta1 = new Aposta(time1, campeonato1, "1", "1000.0");
        aposta2 = new Aposta(time1, campeonato1, "1", "1000.0");
    }

    @Test
    void testGetColocacaoTime() {
        assertEquals("1", aposta1.getColocacao());
    }

    @Test
    void testGetValorAposta() {
        assertEquals("1000.0", aposta1.getValor());
    }

    @Test
    void testEquals() {
        assertEquals(aposta1, aposta2);

        Aposta aposta3 = new Aposta(time1, campeonato1, "2", "1000.0");
        assertNotEquals(aposta1, aposta3);

        Aposta aposta4 = new Aposta(time1, campeonato1, "1", "2000.0");
        assertNotEquals(aposta1, aposta4);
    }

    @Test
    void testHashCode() {
        assertEquals(aposta1.hashCode(), aposta2.hashCode());

        Aposta aposta3 = new Aposta(time1, campeonato1, "2", "1000.0");
        assertNotEquals(aposta1.hashCode(), aposta3.hashCode());

        Aposta aposta4 = new Aposta(time1, campeonato1, "1", "2000.0");
        assertNotEquals(aposta1.hashCode(), aposta4.hashCode());
    }

    @Test
    void testToString() {
        String expected = "[001] Clube de Regatas do Flamengo / Urubu | Brasileirao - 1/20 | R$1000.0";
        assertEquals(expected, aposta1.toString());
    }

    @Test
    void testGetTime() {
        assertEquals(time1, aposta1.getTime());
    }
}