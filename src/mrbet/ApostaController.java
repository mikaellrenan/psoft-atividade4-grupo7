package mrbet;

import java.util.ArrayList;
import java.util.List;

public class ApostaController {
    private List<Aposta> apostas;
    private TimeController timeController;
    private CampeonatoController campeonatoController;

    public ApostaController(TimeController timeController, CampeonatoController campeonatoController) {
        this.apostas = new ArrayList<>();
        this.timeController = timeController;
        this.campeonatoController = campeonatoController;
    }

    public String tentarSorte(String idTime, String nomeCamp, int colocacao, double valor) {
        if (!timeController.existeTime(idTime)) 
            return "O TIME NAO EXISTE!";
        if (!campeonatoController.existeCampeonato(nomeCamp)) 
            return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatoController.buscarCampeonato(nomeCamp);
        Time time = timeController.buscarTime(idTime);

        if (!camp.hasTeam(time)) 
            return "O TIME NAO ESTA NO CAMPEONATO!";
        if (colocacao < 1 || colocacao > camp.getMaxParticipants()) 
            return "APOSTA NAO REGISTRADA!";

        apostas.add(new Aposta(time, camp, String.valueOf(colocacao), String.valueOf(valor)));
        return "APOSTA REGISTRADA!";
    }

    public String statusAposta() {
        if (apostas.isEmpty()) return "NAO TEM APOSTAS REGISTRADAS!";

        StringBuilder sb = new StringBuilder("Apostas: |\n");
        for (Aposta aposta : apostas) {
            sb.append(aposta.toString()).append(" | \n");
        }
        return sb.toString().trim();
    }
}