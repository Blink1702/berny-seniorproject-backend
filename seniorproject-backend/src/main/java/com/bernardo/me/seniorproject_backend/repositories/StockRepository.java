package com.bernardo.me.seniorproject_backend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardo.me.seniorproject_backend.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    List<Stock> findByType(String type);
}
