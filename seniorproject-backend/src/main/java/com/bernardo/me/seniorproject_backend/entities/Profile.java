package com.bernardo.me.seniorproject_backend.entities;

import java.util.List;

import com.bernardo.me.seniorproject_backend.interfaces.dtos.ProfileDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileid;
    @OneToOne
    @JoinColumn(name = "user")
    private Users user;
    private String luId;
    private String email;

    public Profile() {
    }

    public Profile(ProfileDTO core) {
        luId = core.getLuid();
        email = core.getEmail();
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getLuid() {
        return luId;
    }

    public void setLuid(String luId) {
        this.luId = luId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
