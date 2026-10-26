package mrbet;

import java.util.HashMap;
import java.util.Map;

public class TimeController {
    private Map<String, Time> times;

    public TimeController() {
        this.times = new HashMap<>();
    }

    public String incluirTime(String id, String nome, String mascote) {
        if (id == null || id.isBlank()) 
            return "Nao pode adicionar o vazio";
        if (times.containsKey(id)) 
            return "TIME JA EXISTE!";
        
        times.put(id, new Time(id, nome, mascote));
        return "INCLUSAO REALIZADA!";
    }

    public String recuperarTime(String id) {
        if (!times.containsKey(id)) 
            return "O TIME NAO EXISTE!";
        return times.get(id).toString();
    }

    public Time buscarTime(String id) {
        return times.get(id);
    }

    public boolean existeTime(String id) {
        return times.containsKey(id);
    }
}