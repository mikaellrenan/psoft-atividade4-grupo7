package mrbet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeTeste {
    private Time time1;
    private Time time2;
    private Time time3;

    @BeforeEach
    void setUp() {
        time1 = new Time("001", "Clube de Regatas do Flamengo", "Urubu");
        time2 = new Time("001", "Clube de Regatas do Flamengo", "Urubu");
        time3 = new Time("002", "Sociedade Esportiva Palmeiras", "Porco");
    }

    @Test
    void testGetNome() {
        assertEquals("Clube de Regatas do Flamengo", time1.getNome());
    }

    @Test
    void testGetIdTime() {
        assertEquals("001", time1.getIdTime());
    }

    @Test
    void testGetMascote() {
        assertEquals("Urubu", time1.getMascote());
    }

    @Test
    void testEquals() {
        assertEquals(time1, time2);
        assertNotEquals(time1, time3);
    }

    @Test
    void testHashCode() {
        assertEquals(time1.hashCode(), time2.hashCode());
        assertNotEquals(time1.hashCode(), time3.hashCode());
    }

    @Test
    void testToString() {
        String expected = "[001] Clube de Regatas do Flamengo / Urubu";
        assertEquals(expected, time1.toString());
    }
}
