package com.codev.locatrash.service;

import com.codev.locatrash.entity.Favori;
import com.codev.locatrash.entity.Trash;
import com.codev.locatrash.entity.request.FavoriteTrash;
import com.codev.locatrash.exception.RessourceException;
import com.codev.locatrash.repository.FavoriRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriService {

    private FavoriRepository favoriRepository;
    private TrashService ts;

    public List<Favori> getFavoris(){
        return favoriRepository.findAll();
    }

    public List<Favori> getFavorisByUser(long id){
        return favoriRepository.findByUserId(id);
    }

    public List<FavoriteTrash> getFavoritesTrashesByUser(long id){
        List<FavoriteTrash> favoriteTrashes = new ArrayList<>();
        List<Favori> favoris = favoriRepository.findByUserId(id);
        for(Favori f : favoris){
            FavoriteTrash ft = new FavoriteTrash();
            ft.setId(f.getId());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            ft.setDate(df.format(f.getDate()));
            ft.setUserId(f.getUserId());
            Trash t = ts.getTrashById(f.getTrashId());
            ft.setTrash(t);
            favoriteTrashes.add(ft);
        }
        return favoriteTrashes;
    }

    public Favori addFavori(long user, String trash){
        Favori f=new Favori();
        f.setUserId(user);
        f.setTrashId(trash);
        f.setDate(new Date());
        return favoriRepository.save(f);
    }

    public void removeFavori(long id){
        Favori f = favoriRepository.findById(id).orElseThrow(
                () -> new RessourceException("Favori", "id", id)
        );
        favoriRepository.delete(f);
    }

}
