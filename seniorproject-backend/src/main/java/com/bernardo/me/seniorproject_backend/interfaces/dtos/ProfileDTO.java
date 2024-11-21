package com.bernardo.me.seniorproject_backend.interfaces.dtos;

import com.bernardo.me.seniorproject_backend.entities.Profile;

public class ProfileDTO {
    private int profileid;
    private String user;
    private String luId;
    private String email;
    private OrdersDTO orders;

    public ProfileDTO() {
    }

    public ProfileDTO(Profile core) {
        profileid = core.getProfileid();
        user = core.getUser().toString();
        luId = core.getLuid();
        email = core.getEmail();
        orders = new OrdersDTO(core.getOrders());
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLuid() {
        return luId;
    }

    public void setLuid(String luId) {
        this.luId = luId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrdersDTO getOrders() {
        return orders;
    }

    public void setOrders(OrdersDTO orders) {
        this.orders = orders;
    }
}
