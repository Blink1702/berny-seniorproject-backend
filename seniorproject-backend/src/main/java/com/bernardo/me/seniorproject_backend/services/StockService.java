package com.bernardo.me.seniorproject_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bernardo.me.seniorproject_backend.entities.Stock;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.StockDTO;
import com.bernardo.me.seniorproject_backend.repositories.StockRepository;
import com.bernardo.me.seniorproject_backend.repositories.UsersRepository;

public class StockService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    StockRepository stockRepository;

    public String save(StockDTO stock) throws WrongStockException {
        Optional<Stock> maybeS = stockRepository.findById(UUID.fromString(stock.getStockid()));
        if (!maybeS.isPresent())
            throw new WrongStockException();

        Stock newStock = new Stock(stock);
        stockRepository.save(newStock);

        return newStock.getStockid().toString();
    }

    /*
     * public ResponseEntity<StockDTO> updateStock(int amount) {
     * stockRepository.
     * }
     */

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findById(UUID id) {
        return stockRepository.findById(id).get();
    }

    public List<Stock> findByType(String type) {
        return stockRepository.findByType(type);
    }

}
