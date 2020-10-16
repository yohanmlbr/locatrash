package com.codev.locatrash.service;

import com.codev.locatrash.entity.Poubelle;
import com.codev.locatrash.entity.request.LocationIQAPIResponse;
import com.codev.locatrash.entity.request.StatsPoubelleRequest;
import com.codev.locatrash.proxies.LocationIQClient;
import com.codev.locatrash.proxies.PoubelleClient;
import com.codev.locatrash.repository.PoubelleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PoubelleService {

    private PoubelleRepository poubelleRepository;
    private PoubelleClient poubelleClient;
    private LocationIQClient locationIQClient;

    public List<Poubelle> getPoubelles(){
        return poubelleRepository.findAll();
    }

    public List<Poubelle> getTrashesFromAPI(){
        return poubelleClient.getAllPoubelles().getValues();
    }

    public void updateTrashes(){
        List<Poubelle> trashes=getTrashesFromAPI();
        for(Poubelle t : trashes){
            try {
                Thread.sleep(1000);
                LocationIQAPIResponse LatLong=locationIQClient.getLatLongTrash(getCompleteTrashAddress(t)).get(0);
                t.setLatitude(LatLong.getLat());
                t.setLongitude(LatLong.getLon());
                poubelleRepository.save(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public String getCompleteTrashAddress(Poubelle p){
        String address="";
        if(!(p.getNumerodansvoie()==null))
            address+=p.getNumerodansvoie()+" ";
        if(!(p.getVoie()==null))
            address+=p.getVoie()+" ";
        if(!(p.getCommune()==null)) {
            address += p.getCommune() + " ";
            if (!(p.getCommune().contains("LYON")))
                address += "Lyon ";
        }
        address+="France";
        return address;
    }
}
