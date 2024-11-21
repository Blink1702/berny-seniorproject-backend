package com.bernardo.me.seniorproject_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.bernardo.me.seniorproject_backend.entities.Stock;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.StockDTO;
import com.bernardo.me.seniorproject_backend.repositories.StockRepository;
import com.bernardo.me.seniorproject_backend.repositories.UsersRepository;

public class StockService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    StockRepository stockRepository;

    public boolean save(StockDTO stock) throws Exception {
        Optional<Stock> maybeS = stockRepository.findById(UUID.fromString(stock.getStockid()));
        if (!maybeS.isPresent())
            return false;

        Stock newStock = new Stock(stock);
        stockRepository.save(newStock);

        return true;
    }

    public List<Stock> findByType(String type) {
        return stockRepository.findByType(type);
    }

}