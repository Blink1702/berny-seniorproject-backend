package com.bernardo.me.seniorproject_backend.interfaces.dtos;

import com.bernardo.me.seniorproject_backend.entities.Stock;

public class StockDTO {
    private String stockid;
    private int amount;
    private String type;
    private String item;
    private String datePurchased;

    public StockDTO() {

    }

    public StockDTO(Stock core) {
        stockid = core.getStockid().toString();
        amount = core.getAmount();
        type = core.getType();
        item = core.getItem();
        datePurchased = core.getDate().toString();
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public String getDate() {
        return datePurchased;
    }

    public void setDate(String datePurchased) {
        this.datePurchased = datePurchased;
    }
}
