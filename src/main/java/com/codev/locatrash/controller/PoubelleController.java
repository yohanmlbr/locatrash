package com.codev.locatrash.controller;

import com.codev.locatrash.entity.Poubelle;
import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.AddUserRequest;
import com.codev.locatrash.entity.request.StatsPoubelleRequest;
import com.codev.locatrash.repository.PoubelleRepository;
import com.codev.locatrash.repository.UserRepository;
import com.codev.locatrash.service.PoubelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poubelle")
public class PoubelleController {

    private PoubelleRepository pr;

    @Autowired
    public PoubelleController(PoubelleRepository poubelleRepository) {
        this.pr = poubelleRepository;
    }

    @GetMapping("/list")
    public List<Poubelle> getPoubelles(){
        PoubelleService ps = new PoubelleService(pr);
        return ps.getPoubelles();
    }

    @GetMapping("/update")
    public void updatePoubelles(){
        PoubelleService ps = new PoubelleService(pr);
        ps.updatePoubelles();
    }

    @GetMapping("/stats")
    public StatsPoubelleRequest statsPoubelles(){
        PoubelleService ps = new PoubelleService(pr);
        return ps.statsPoubelles();
    }
}
