package com.codev.locatrash.service;

import com.codev.locatrash.entity.Trash;
import com.codev.locatrash.entity.request.LocationIQAPIResponse;
import com.codev.locatrash.exception.RessourceException;
import com.codev.locatrash.proxies.LocationIQClient;
import com.codev.locatrash.proxies.TrashClient;
import com.codev.locatrash.repository.TrashRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class TrashService {

    private TrashRepository trashRepository;
    private TrashClient trashClient;
    private LocationIQClient locationIQClient;

    public List<Trash> getTrashes(){
        return trashRepository.findAll();
    }

    public List<Trash> getTrashesFromAPI(){
        return trashClient.getAllPoubelles().getValues();
    }

    public Trash getTrashById(String id){
        return trashRepository.findById(id).orElseThrow(
            () -> new RessourceException("Trash", "id", id)
        );
    }

    public void updateTrashes(){
        List<Trash> trashes=getTrashesFromAPI();
        for(Trash t : trashes){
            try {
                Thread.sleep(1000);
                LocationIQAPIResponse LatLong=locationIQClient.getLatLongTrash(getCompleteTrashAddress(t)).get(0);
                t.setLatitude(LatLong.getLat());
                t.setLongitude(LatLong.getLon());
                trashRepository.save(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String,Long> countTrashesByCommune(){
        HashMap<String,Long> hm=new HashMap<>();
        long total=0;
        for(Object[] obj : trashRepository.countTrashesByCommune()){
            //System.out.println(obj[0].toString()+" "+obj[1].toString());
            hm.put((String)obj[0],(Long)obj[1]);
            total+=(Long)obj[1];
        }
        hm.put("TOTAL",total);
        return hm;
    }

    public String getCompleteTrashAddress(Trash p){
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
