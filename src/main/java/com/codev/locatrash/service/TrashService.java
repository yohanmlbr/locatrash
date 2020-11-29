package com.codev.locatrash.service;

import com.codev.locatrash.entity.Trash;
import com.codev.locatrash.entity.request.LocationIQAPIResponse;
import com.codev.locatrash.entity.request.TrashCommune;
import com.codev.locatrash.exception.RessourceException;
import com.codev.locatrash.proxies.LocationIQClient;
import com.codev.locatrash.proxies.TrashClient;
import com.codev.locatrash.repository.TrashRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public int getTrashesCount(){
        return getTrashes().size();
    }

    public Trash getTrashById(String id){
        return trashRepository.findById(id).orElseThrow(
            () -> new RessourceException("Trash", "id", id)
        );
    }

    public String getNearestTrash(double lat, double lon){
        List<Trash> trashes =  trashRepository.findByLatLon(lat,lon);
        return trashes.isEmpty() ? null : trashes.get(0).getIdentifiant();
    }

    public void updateTrashes(){
        List<Trash> trashes=getTrashesFromAPI();
        for(Trash t : trashes){
            try {
                System.out.println("TRASH "+t.getIdentifiant()+", GID "+t.getGid());
                if(!trashExists(t.getIdentifiant()) && !forbiddenAddress(t.getVoie())){
                    Thread.sleep(1000);
                    LocationIQAPIResponse LatLong=locationIQClient.getLatLongTrash(getCompleteTrashAddress(t)).get(0);
                    t.setLatitude(LatLong.getLat());
                    t.setLongitude(LatLong.getLon());
                    trashRepository.save(t);
                    System.out.println("Trash "+t.getIdentifiant()+" added!");
                }
                else{
                    System.out.println("Trash "+t.getIdentifiant()+" already exists!");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean trashExists(String id){
        try{
            getTrashById(id);
            return true;
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    private boolean forbiddenAddress(String voie){
        boolean ok=false;
        if(voie!=null && (voie.equals("Berge Clara Campoamor") || voie.equals("Berge Aletta Jacobs") || voie.equals("Berge Renata Tebaldi"))){
            ok=true;
        }
        return ok;
    }

    public List<TrashCommune> countTrashesByCommune(){
        List<TrashCommune> trashesByCommune=new ArrayList<>();
        for(Object[] obj : trashRepository.countTrashesByCommune()){
           TrashCommune tc=new TrashCommune();
           tc.setCommune((String)obj[0]);
           tc.setTrashes((Long)obj[1]);
           trashesByCommune.add(tc);
        }
        return trashesByCommune;
    }

    private String getCompleteTrashAddress(Trash p){
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
