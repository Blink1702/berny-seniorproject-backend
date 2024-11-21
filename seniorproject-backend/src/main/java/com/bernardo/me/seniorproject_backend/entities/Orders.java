package com.bernardo.me.seniorproject_backend.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.bernardo.me.seniorproject_backend.interfaces.dtos.OrdersDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderid;
    @ManyToOne
    private Users user;
    private String item;
    private boolean fulfilled;
    private LocalDate date;

    public Orders() {
    }

    public Orders(OrdersDTO core) {
        item = core.getItem();
        fulfilled = core.getFulfilled();
        date = LocalDate.parse(core.getDate());
    }

    public UUID getOrderid() {
        return orderid;
    }

    public void setOrderid(UUID orderid) {
        this.orderid = orderid;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}