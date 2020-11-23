package com.codev.locatrash.controller;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.AddUserRequest;
import com.codev.locatrash.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService us;

    @GetMapping("/list")
    public List<User> getUsers(){
        return us.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody AddUserRequest addUserRequest){
        us.addUser(addUserRequest);
    }
}
