package com.bernardo.me.seniorproject_backend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardo.me.seniorproject_backend.entities.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    List<Users> findByUsername(String name);
}
