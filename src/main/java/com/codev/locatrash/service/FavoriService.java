package com.codev.locatrash.service;

import com.codev.locatrash.entity.Favori;
import com.codev.locatrash.exception.RessourceException;
import com.codev.locatrash.repository.FavoriRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriService {

    private FavoriRepository favoriRepository;

    public List<Favori> getFavoris(){
        return favoriRepository.findAll();
    }

    public List<Favori> getFavorisByUser(long id){
        return favoriRepository.findByUserId(id);
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
