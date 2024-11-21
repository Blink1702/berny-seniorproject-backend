package com.bernardo.me.seniorproject_backend.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.me.seniorproject_backend.entities.Users;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.UsersDTO;
import com.bernardo.me.seniorproject_backend.security.JwtService;
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

}
