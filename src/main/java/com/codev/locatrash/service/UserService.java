package com.codev.locatrash.service;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.SignUpUser;
import com.codev.locatrash.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(SignUpUser signUpUser){
        User user = new User();
        user.setEmail(signUpUser.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
        user.setName(signUpUser.getName());
        user.setSurname(signUpUser.getSurname());
        return userRepository.save(user);
    }
}
