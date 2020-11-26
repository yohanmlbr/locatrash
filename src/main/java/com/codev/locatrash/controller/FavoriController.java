package com.codev.locatrash.controller;

import com.codev.locatrash.entity.Favori;
import com.codev.locatrash.entity.request.FavoriteTrash;
import com.codev.locatrash.service.FavoriService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/favoris")
public class FavoriController {

    private FavoriService fs;

    @GetMapping("/list")
    public List<Favori> getFavoris(){
        return fs.getFavoris();
    }

    @GetMapping("/list/{user}")
    public List<Favori> getFavorisByUser(@PathVariable long user){
        return fs.getFavorisByUser(user);
    }

    @GetMapping("/trashes/{user}")
    public List<FavoriteTrash> getFavoritesTrashesByUser(@PathVariable long user){
        return fs.getFavoritesTrashesByUser(user);
    }

    @PostMapping("/add/{user}/{trash}")
    public Favori addFavori(@PathVariable long user, @PathVariable String trash){
        return fs.addFavori(user,trash);
    }
    @DeleteMapping("/remove/{id}")
    public void removeFavori(@PathVariable long id){
        fs.removeFavori(id);
    }
}
