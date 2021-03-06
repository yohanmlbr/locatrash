package com.codev.locatrash.controller;

import com.codev.locatrash.entity.Trash;
import com.codev.locatrash.entity.request.TrashCommune;
import com.codev.locatrash.service.TrashService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/trashes")
public class TrashController {

    private TrashService ts;

    @GetMapping("/list")
    public List<Trash> getTrashes(){
        return ts.getTrashes();
    }

    @GetMapping("/listAPI")
    public List<Trash> getTrashesFromAPI(){
        return ts.getTrashesFromAPI();
    }

    @GetMapping("/trash/{id}")
    public Trash getTrashById(@PathVariable(value = "id") String id) {
        return ts.getTrashById(id);
    }

    @GetMapping("/update")
    public void updateTrashes(){
        ts.updateTrashes();
    }

    @GetMapping("/countByCommune")
    public List<TrashCommune> countTrashesByCommune(){
        return ts.countTrashesByCommune();
    }

    @GetMapping("/nearest/{lat}/{lon}")
    public String getNearestTrash(@PathVariable double lat, @PathVariable double lon){
        return ts.getNearestTrash(lat,lon);
    }

    @GetMapping("/exists/{id}")
    public boolean getTrashExists(@PathVariable String id){
        return ts.trashExists(id);
    }

    @GetMapping("/count")
    public int getTrashesCount(){
        return ts.getTrashesCount();
    }
}
