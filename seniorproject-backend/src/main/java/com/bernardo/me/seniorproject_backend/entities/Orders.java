package com.bernardo.me.seniorproject_backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.bernardo.me.seniorproject_backend.interfaces.dtos.OrdersDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(45)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID orderid;
    @ManyToOne
    private Users user;
    private String item;
    private boolean fulfilled;
    private LocalDateTime date;

    public Orders() {
    }

    public Orders(OrdersDTO core) {
        item = core.getItem();
        fulfilled = core.getFulfilled();
        date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}