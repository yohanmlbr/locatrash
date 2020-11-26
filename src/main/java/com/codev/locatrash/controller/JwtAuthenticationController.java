package com.codev.locatrash.controller;

import java.util.Objects;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.entity.request.LogInUser;
import com.codev.locatrash.entity.request.SignUpUser;
import com.codev.locatrash.repository.UserRepository;
import com.codev.locatrash.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.codev.locatrash.service.JwtUserDetailsService;
import com.codev.locatrash.config.JwtTokenUtil;
import com.codev.locatrash.entity.JwtResponse;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserService us;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LogInUser liu)
            throws Exception {
        try {
            System.out.println("User tries to login : "+liu.getEmail()+" "+liu.getPassword());
            UserDetails userDetails= appelAuthentication(liu.getEmail(), liu.getPassword());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpUser suu){
        User u=us.addUser(suu);
        LogInUser liu = new LogInUser(suu);
        try {
            return createAuthenticationToken(liu);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    private UserDetails appelAuthentication(String email, String password) throws Exception {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();
            return userDetails;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}