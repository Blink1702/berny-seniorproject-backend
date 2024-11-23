package com.bernardo.me.seniorproject_backend.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.me.seniorproject_backend.entities.Stock;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.StockDTO;
import com.bernardo.me.seniorproject_backend.services.StockService;
import com.bernardo.me.seniorproject_backend.services.WrongStockException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StockDTO> save(Authentication authentication, StockDTO stock) {
        String key;
        try {
            key = stockService.save(stock);
            stock.setStockid(key);
        } catch (WrongStockException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stock);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getStock() {
        List<Stock> stock = stockService.findAll();
        List<StockDTO> results = new ArrayList<StockDTO>();
        for (Stock s : stock) {
            results.add(new StockDTO(s));
        }
        return ResponseEntity.ok().body(results);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable UUID id) {
        Stock stock = stockService.findById(id);
        StockDTO result = new StockDTO(stock);
        return ResponseEntity.ok().body(result);
    }

}
