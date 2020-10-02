package com.codev.locatrash.service;

import com.codev.locatrash.entity.Poubelle;
import com.codev.locatrash.entity.request.StatsPoubelleRequest;
import com.codev.locatrash.repository.PoubelleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PoubelleService {

    private PoubelleRepository poubelleRepository;

    public PoubelleService(PoubelleRepository poubelleRepository){
        this.poubelleRepository=poubelleRepository;
    }

    public List<Poubelle> getPoubelles(){
        return poubelleRepository.findAll();
    }

    public void updatePoubelles(){
        List<Poubelle> poubelles=getPoubelles();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode content= mapper.readTree(new File("D:\\Documents\\#PolyLyon\\Informatique\\5A\\CODEV\\all_poubelles.json"));
            JsonNode values = content.path("values");
            for(JsonNode poubelle : values){
                Poubelle p = mapper.readValue(poubelle.toString(),Poubelle.class);
                if(isPoubelleNew(p,poubelles)) {
                    poubelleRepository.save(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPoubelleNew(Poubelle poubelle, List<Poubelle> poubelles){
        for(Poubelle p : poubelles){
            if(p.getIdentifiant().equals(poubelle.getIdentifiant())){
                System.out.println("Poubelle "+poubelle.getIdentifiant()+" already exist");
                return false;
            }
        }
        return true;
    }

    public StatsPoubelleRequest statsPoubelles(){
        List<Poubelle> poubelles = poubelleRepository.findAll();
        HashMap<String,Integer> poubellesBySector = new HashMap<>();
        poubellesBySector.put("TOTAL",0);
        for(Poubelle p : poubelles){
            if(!poubellesBySector.containsKey(p.getCommune())){
                poubellesBySector.put(p.getCommune(),1);
            }
            else{
                poubellesBySector.replace(p.getCommune(),poubellesBySector.get(p.getCommune())+1);
            }
            poubellesBySector.put("TOTAL",poubellesBySector.get("TOTAL")+1);
        }
        return new StatsPoubelleRequest(poubellesBySector);
    }
}
