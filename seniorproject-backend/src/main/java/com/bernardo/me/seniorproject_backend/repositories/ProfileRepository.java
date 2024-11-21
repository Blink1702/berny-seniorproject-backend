package com.bernardo.me.seniorproject_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardo.me.seniorproject_backend.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
