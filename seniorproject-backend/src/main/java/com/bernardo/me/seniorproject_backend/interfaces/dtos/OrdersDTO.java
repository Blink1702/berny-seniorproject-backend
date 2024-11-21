package com.bernardo.me.seniorproject_backend.interfaces.dtos;

import com.bernardo.me.seniorproject_backend.entities.Orders;

public class OrdersDTO {
    private String orderid;
    private String user;
    private String item;
    private boolean fulfilled;
    private String date;

    public OrdersDTO() {

    }

    public OrdersDTO(Orders core) {
        orderid = core.getOrderid().toString();
        user = core.getUser().toString();
        item = core.getItem().toString();
        fulfilled = core.getFulfilled();
        date = core.getDate().toString();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}