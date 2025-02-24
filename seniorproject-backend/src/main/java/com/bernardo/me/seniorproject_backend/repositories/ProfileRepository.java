package com.bernardo.me.seniorproject_backend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardo.me.seniorproject_backend.entities.Profile;
import com.bernardo.me.seniorproject_backend.entities.Users;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUser(Users user);

}
