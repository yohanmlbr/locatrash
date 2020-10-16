package com.codev.locatrash.controller;

import com.codev.locatrash.entity.Poubelle;
import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.AddUserRequest;
import com.codev.locatrash.entity.request.StatsPoubelleRequest;
import com.codev.locatrash.proxies.PoubelleClient;
import com.codev.locatrash.repository.PoubelleRepository;
import com.codev.locatrash.repository.UserRepository;
import com.codev.locatrash.service.PoubelleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/poubelle")
public class PoubelleController {

    private PoubelleService ps;

    @GetMapping("/list")
    public List<Poubelle> getPoubelles(){
        return ps.getPoubelles();
    }

    @GetMapping("/listAPI")
    public List<Poubelle> getTrashesFromAPI(){
        return ps.getTrashesFromAPI();
    }

    @GetMapping("/update")
    public void updateTrashes(){
        ps.updateTrashes();
    }

    @GetMapping("/stats")
    public StatsPoubelleRequest statsPoubelles(){
        return ps.statsPoubelles();
    }

}
