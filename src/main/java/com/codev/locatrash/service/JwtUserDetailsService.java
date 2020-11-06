package com.codev.locatrash.service;

import com.codev.locatrash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    // on initialise
    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    com.codev.locatrash.entity.User user = null;
    // on accède à l'utilisateur
    user = userRepository.searchEmail(email);
    if (user != null) {
        return new User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    } else {
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
}