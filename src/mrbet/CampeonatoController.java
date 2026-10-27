package mrbet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampeonatoController {
    private Map<String, Campeonato> campeonatos;

    public CampeonatoController() {
        this.campeonatos = new HashMap<>();
    }

    public String incluirCampeonato(String nome, int maxParticipants) {
        if (nome == null || nome.isBlank()) 
            return "Nao pode adicionar o vazio";
        if (campeonatos.containsKey(nome)) 
            return "CAMPEONATO JA EXISTE!";
        campeonatos.put(nome, new Campeonato(nome, maxParticipants));
        return "CAMPEONATO ADICIONADO!";
    }

    public String incluirTimeCampeonato(String idTime, String nomeCamp, TimeController timeController) {
        if (!timeController.existeTime(idTime)) 
            return "O TIME NAO EXISTE!";
        if (!campeonatos.containsKey(nomeCamp)) 
            return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatos.get(nomeCamp);
        Time time = timeController.buscarTime(idTime);

        if (camp.hasTeam(time)) 
            return "O TIME JA FOI INCLUIDO ANTERIORMENTE!";
        if (camp.getCurrentParticipants() >= camp.getMaxParticipants())
            return "TODOS OS TIMES DESSE CAMPEONATO JA FORAM INCLUIDOS!";

        camp.addTeam(time);
        return "TIME INCLUIDO NO CAMPEONATO!";
    }

    public String verificaTimeCampeonato(String idTime, String nomeCamp, TimeController timeController) {
        if (!timeController.existeTime(idTime)) 
            return "O TIME NAO EXISTE!";
        if (!campeonatos.containsKey(nomeCamp)) 
            return "CAMPEONATO NAO EXISTE!";

        Campeonato camp = campeonatos.get(nomeCamp);
        Time time = timeController.buscarTime(idTime);

        return camp.hasTeam(time) ? "O TIME ESTA NO CAMPEONATO!" : "O TIME NAO ESTA NO CAMPEONATO!";
    }

    public String campsParticipando(String idTime, TimeController timeController) {
        if (!timeController.existeTime(idTime)) 
            return "O TIME NAO EXISTE!";

        Time time = timeController.buscarTime(idTime);
        List<String> camps = new ArrayList<>();

        for (Campeonato camp : campeonatos.values()) {
            if (camp.hasTeam(time)) camps.add(camp.toString());
        }

        if (camps.isEmpty()) 
            return "O TIME NAO ESTA EM NENHUM CAMPEONATO!";

        return "Campeonatos do " + time.getNome() + ":\n" + String.join("\n", camps);
    }

    public Campeonato buscarCampeonato(String nome) {
        return campeonatos.get(nome);
    }

    public boolean existeCampeonato(String nome) {
        return campeonatos.containsKey(nome);
    }
}