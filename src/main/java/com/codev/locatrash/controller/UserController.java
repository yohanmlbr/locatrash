package com.codev.locatrash.controller;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.AddUserRequest;
import com.codev.locatrash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody AddUserRequest addUserRequest){
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setSurname(addUserRequest.getSurname());
        userRepository.save(user);
    }
}
