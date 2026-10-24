package mrbet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MrBetTeste {
    private MrBetController sistema;
    private String time1Id = "250_PB";
    private String time1Nome = "Nacional de Patos";
    private String time1Mascote = "Canario";
    
    private String time2Id = "252_PB";
    private String time2Nome = "Sport Lagoa Seca";
    private String time2Mascote = "Carneiro";

    @BeforeEach
    public void setUp() {
        sistema = new MrBetController();
    }

    @Test
    public void testIncluirTimeComSucesso() {
        String mensagem = sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        assertEquals("INCLUSAO REALIZADA!", mensagem);
    }

    @Test
    public void testIncluirTimeCodigoVazio() {
        String mensagem = sistema.incluirTime("", time2Nome, time2Mascote);
        assertEquals("Nao pode adicionar o vazio", mensagem);
    }

    @Test
    public void testIncluirTimeJaExistente() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        String mensagem = sistema.incluirTime(time1Id, "Outro Nome", "Outro Mascote");
        assertEquals("TIME JA EXISTE!", mensagem);
    }

    @Test
    public void testRecuperarTimeComSucesso() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        String infoTime = sistema.recuperarTime(time1Id);
        assertEquals("[250_PB] Nacional de Patos / Canario", infoTime);
    }

    @Test
    public void testRecuperarTimeInexistente() {
        String infoTime = sistema.recuperarTime("TIME_INEXISTENTE");
        assertEquals("O TIME NAO EXISTE!", infoTime);
    }

    @Test
    public void testIncluirCampeonatoComSucesso() {
        String mensagem = sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        assertEquals("CAMPEONATO ADICIONADO!", mensagem);
    }

    @Test
    public void testIncluirCampeonatoNomeVazio() {
        String mensagem = sistema.incluirCampeonato("", 10);
        assertEquals("Nao pode adicionar o vazio", mensagem);
    }

    @Test
    public void testIncluirCampeonatoJaExistente() {
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        String mensagem = sistema.incluirCampeonato("Brasileirao Serie A 2023", 30);
        assertEquals("CAMPEONATO JA EXISTE!", mensagem);
    }

    @Test
    public void testIncluirTimeCampeonatoComSucesso() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        String mensagem = sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        assertEquals("TIME INCLUIDO NO CAMPEONATO!", mensagem);
    }

    @Test
    public void testIncluirTimeCampeonatoTimeInexistente() {
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        String mensagem = sistema.incluirTimeCampeonato("TIME_INEXISTENTE", "Brasileirao Serie A 2023");
        assertEquals("O TIME NAO EXISTE!", mensagem);
    }

    @Test
    public void testIncluirTimeCampeonatoCampeonatoInexistente() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        String mensagem = sistema.incluirTimeCampeonato(time1Id, "CAMPEONATO_INEXISTENTE");
        assertEquals("CAMPEONATO NAO EXISTE!", mensagem);
    }

    @Test
    public void testIncluirTimeCampeonatoCampeonatoCheio() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirTime(time2Id, time2Nome, time2Mascote);
        sistema.incluirCampeonato("Campeonato Pequeno", 1);
        
        String mensagem1 = sistema.incluirTimeCampeonato(time1Id, "Campeonato Pequeno");
        assertEquals("TIME INCLUIDO NO CAMPEONATO!", mensagem1);
        
        String mensagem2 = sistema.incluirTimeCampeonato(time2Id, "Campeonato Pequeno");
        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JA FORAM INCLUIDOS!", mensagem2);
    }

    @Test
    public void testIncluirTimeCampeonatoTimeJaIncluido() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        
        String mensagem = sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        assertEquals("O TIME JA FOI INCLUIDO ANTERIORMENTE!", mensagem);
    }

    @Test
    public void testVerificaTimeCampeonatoTimeEsta() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        
        String resultado = sistema.verificaTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        assertEquals("O TIME ESTA NO CAMPEONATO!", resultado);
    }

    @Test
    public void testVerificaTimeCampeonatoTimeNaoEsta() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        
        String resultado = sistema.verificaTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        assertEquals("O TIME NAO ESTA NO CAMPEONATO!", resultado);
    }

    @Test
    public void testVerificaTimeCampeonatoTimeInexistente() {
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        String resultado = sistema.verificaTimeCampeonato("TIME_INEXISTENTE", "Brasileirao Serie A 2023");
        assertEquals("O TIME NAO EXISTE!", resultado);
    }

    @Test
    public void testVerificaTimeCampeonatoCampeonatoInexistente() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        String resultado = sistema.verificaTimeCampeonato(time1Id, "CAMPEONATO_INEXISTENTE");
        assertEquals("CAMPEONATO NAO EXISTE!", resultado);
    }

    @Test
    public void testCampsParticipandoComCampeonatos() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        
        String resultado = sistema.campsParticipando(time1Id);
        assertTrue(resultado.contains("Campeonatos do Nacional de Patos"));
        assertTrue(resultado.contains("Brasileirao Serie A 2023 - 1/20"));
    }

    @Test
    public void testCampsParticipandoSemCampeonatos() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        
        String resultado = sistema.campsParticipando(time1Id);
        assertEquals("O TIME NAO ESTA EM NENHUM CAMPEONATO!", resultado);
    }

    @Test
    public void testCampsParticipandoTimeInexistente() {
        String resultado = sistema.campsParticipando("TIME_INEXISTENTE");
        assertEquals("O TIME NAO EXISTE!", resultado);
    }

    @Test
    public void testTentarSorteComSucesso() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        
        String resultado = sistema.tentarSorte(time1Id, "Brasileirao Serie A 2023", 1, 1000.0);
        assertEquals("APOSTA REGISTRADA!", resultado);
    }

    @Test
    public void testTentarSorteColocacaoInvalida() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        
        String resultado = sistema.tentarSorte(time1Id, "Brasileirao Serie A 2023", 21, 1000.0);
        assertEquals("APOSTA NAO REGISTRADA!", resultado);
    }

    @Test
    public void testTentarSorteTimeNaoNoCampeonato() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        
        String resultado = sistema.tentarSorte(time1Id, "Brasileirao Serie A 2023", 1, 1000.0);
        assertEquals("O TIME NAO ESTA NO CAMPEONATO!", resultado);
    }

    @Test
    public void testStatusApostaSemApostas() {
        String resultado = sistema.statusAposta();
        assertEquals("NAO TEM APOSTAS REGISTRADAS!", resultado);
    }

    @Test
    public void testStatusApostaComApostas() {
        sistema.incluirTime(time1Id, time1Nome, time1Mascote);
        sistema.incluirCampeonato("Brasileirao Serie A 2023", 20);
        sistema.incluirTimeCampeonato(time1Id, "Brasileirao Serie A 2023");
        sistema.tentarSorte(time1Id, "Brasileirao Serie A 2023", 1, 1000.0);
        
        String resultado = sistema.statusAposta();
        assertTrue(resultado.contains("Apostas: |"));
        assertTrue(resultado.contains("Nacional de Patos"));
        assertTrue(resultado.contains("R$1000.0"));
    }
}
