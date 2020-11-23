package com.codev.locatrash.controller;

import java.util.Objects;

import com.codev.locatrash.entity.User;
import com.codev.locatrash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codev.locatrash.service.JwtUserDetailsService;
import com.codev.locatrash.config.JwtTokenUtil;
import com.codev.locatrash.entity.JwtResponse;

@RequestMapping("/auth")
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    private UserRepository userRepository;

    // on initialise
    @Autowired
    public JwtAuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // auhentification  qui va généré un jeton
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user)
            throws Exception {
        try {
            // On contrôle l'utilisateur
            System.out.println("User tries to login : "+user.getEmail()+" "+user.getPassword());
            UserDetails userDetails= appelAuthentication(user.getEmail(), user.getPassword());
            // on récupère les informations
            // nouvel accès à la base de données
            //final UserDetails userDetails = userDetailsService.loadUserByUsername(unUti.getNomUtil());
            // On génère le jeton
            final String token = jwtTokenUtil.generateToken(userDetails);
            // on retourne le jeton dans un flux json
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Demande d'authentification à l'aide de l'objet instancié précédemment
    // La méthode authenticate() appellera la méthode loadUserByUsername() de la classe UserDetailsServiceImpl
    // L'objet autentication contiendra l'objet userDetails dans la propriété principal
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