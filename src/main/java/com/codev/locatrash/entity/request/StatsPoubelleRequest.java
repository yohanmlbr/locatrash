package com.codev.locatrash.entity.request;

import java.util.HashMap;

public class StatsPoubelleRequest {

    private HashMap<String, Integer> poubellesBySector;

    public StatsPoubelleRequest(HashMap<String, Integer> poubellesBySector){
        this.poubellesBySector=poubellesBySector;
    }

    public HashMap<String, Integer> getPoubellesBySector() {
        return poubellesBySector;
    }

    public void setPoubellesBySector(HashMap<String, Integer> poubellesBySector) {
        this.poubellesBySector = poubellesBySector;
    }
}
