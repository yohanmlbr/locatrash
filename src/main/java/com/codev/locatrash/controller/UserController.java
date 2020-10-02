package com.codev.locatrash.controller;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.AddUserRequest;
import com.codev.locatrash.repository.UserRepository;
import com.codev.locatrash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository ur;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.ur = userRepository;
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        UserService us = new UserService(ur);
        return us.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody AddUserRequest addUserRequest){
        UserService us = new UserService(ur);
        us.addUser(addUserRequest);
    }
}
