
package com.bernardo.me.seniorproject_backend.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bernardo.me.seniorproject_backend.interfaces.dtos.StockDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID stockid;
    private Integer amount;
    private String type;
    private String item;
    private LocalDateTime datePurchased;

    public Stock() {

    }

    public Stock(StockDTO core) {
        amount = core.getAmount();
        type = core.getType();
        item = core.getItem();
        datePurchased = LocalDateTime.parse(core.getDate());
    }

    public UUID getStockid() {
        return stockid;
    }

    public void setStockid(UUID stockid) {
        this.stockid = stockid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public LocalDateTime getDate() {
        return datePurchased;
    }

    public void setDate(LocalDateTime datePurchased) {
        this.datePurchased = datePurchased;
    }

}