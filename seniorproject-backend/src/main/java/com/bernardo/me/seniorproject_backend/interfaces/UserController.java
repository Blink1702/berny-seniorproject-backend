package com.bernardo.me.seniorproject_backend.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.me.seniorproject_backend.entities.Orders;
import com.bernardo.me.seniorproject_backend.entities.Profile;
import com.bernardo.me.seniorproject_backend.entities.Users;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.OrdersDTO;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.ProfileDTO;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.UsersDTO;
import com.bernardo.me.seniorproject_backend.security.JwtService;
import com.bernardo.me.seniorproject_backend.security.PantryUserDetails;
import com.bernardo.me.seniorproject_backend.security.WrongUserException;
import com.bernardo.me.seniorproject_backend.services.DuplicateException;
import com.bernardo.me.seniorproject_backend.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService us;
    private JwtService jwt;

    public UserController(UserService us, JwtService jwt) {
        this.us = us;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<UsersDTO> checkLogin(@RequestBody UsersDTO user) {
        Users result = us.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
        }
        String token = jwt.makeJwt(result.getUserid().toString());
        user.setToken(token);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> save(@RequestBody UsersDTO user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        String key;
        try {
            key = us.save(user);
        } catch (DuplicateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
        String token = jwt.makeJwt(key);
        user.setToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileDTO> saveProfile(Authentication authentication, @RequestBody ProfileDTO profile) {
        PantryUserDetails details = (PantryUserDetails) authentication.getPrincipal();
        UUID id = UUID.fromString(details.getUsername());
        try {
            us.saveProfile(id, profile);
        } catch (WrongUserException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(profile);
        } catch (DuplicateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(profile);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(Authentication authentication) {
        PantryUserDetails details = (PantryUserDetails) authentication.getPrincipal();
        UUID id = UUID.fromString(details.getUsername());
        Profile result = us.findProfile(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ProfileDTO response = new ProfileDTO(result);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/allProfiles")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        List<Profile> profiles = us.findProfiles();
        List<ProfileDTO> results = new ArrayList<ProfileDTO>();
        for (Profile p : profiles) {
            results.add(new ProfileDTO(p));
        }
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("profile/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable UUID id) {
        Profile profile = us.findProfile(id);
        ProfileDTO result = new ProfileDTO(profile);
        return ResponseEntity.ok().body(result);
    }

}
