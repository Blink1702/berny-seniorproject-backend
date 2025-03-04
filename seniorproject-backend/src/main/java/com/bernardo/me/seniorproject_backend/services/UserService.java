package com.bernardo.me.seniorproject_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardo.me.seniorproject_backend.entities.Orders;
import com.bernardo.me.seniorproject_backend.entities.Profile;
import com.bernardo.me.seniorproject_backend.entities.Users;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.OrdersDTO;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.ProfileDTO;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.UsersDTO;
import com.bernardo.me.seniorproject_backend.repositories.OrdersRepository;
import com.bernardo.me.seniorproject_backend.repositories.ProfileRepository;
import com.bernardo.me.seniorproject_backend.repositories.UsersRepository;
import com.bernardo.me.seniorproject_backend.security.WrongUserException;

@Service
public class UserService {
    @Autowired
    PasswordService passwordService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public String save(UsersDTO user) throws DuplicateException {
        List<Users> existing = usersRepository.findByUsername(user.getUsername());
        if (existing.size() > 0)
            throw new DuplicateException();

        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        String hash = passwordService.hashPassword(user.getPassword());
        newUser.setPassword(hash);
        usersRepository.save(newUser);
        return newUser.getUserid().toString();
    }

    public Users findByUsernameAndPassword(String username, String password) {
        List<Users> existing = usersRepository.findByUsername(username);
        if (existing.size() != 1) {
            return null;
        }
        Users u = existing.get(0);
        if (passwordService.verifyHash(password, u.getPassword())) {
            u.setPassword("Undisclosed");
        } else {
            u = null;
        }
        return u;
    }

    public void saveProfile(UUID userid, ProfileDTO profile) throws WrongUserException, DuplicateException {
        Optional<Users> maybeUser = usersRepository.findById(userid);
        if (maybeUser.isEmpty())
            throw new WrongUserException();

        Users user = maybeUser.get();
        if (user.getProfile() != null)
            throw new DuplicateException();

        Profile newProfile = new Profile(profile);
        newProfile.setUser(user);
        profileRepository.save(newProfile);
    }

    public Profile findProfile(UUID userid) {
        Optional<Users> maybeUser = usersRepository.findById(userid);
        if (maybeUser.isEmpty())
            return null;

        return maybeUser.get().getProfile();
    }

    public List<Profile> findProfiles() {
        return profileRepository.findAll();
    }

    public String saveOrder(UUID userid, OrdersDTO order) throws WrongUserException {
        Optional<Users> maybeUser = usersRepository.findById(userid);
        if (maybeUser.isEmpty())
            throw new WrongUserException();

        Users user = maybeUser.get();
        Orders newOrders = new Orders(order);
        newOrders.setUser(user);
        newOrders.setFulfilled(false);
        ordersRepository.save(newOrders);

        return newOrders.getOrderid().toString();
    }

    public List<Orders> findOrders() {
        return ordersRepository.findAll();
    }

    public List<Orders> findOrderByUser(UUID userid) {
        Optional<Users> maybeUser = usersRepository.findById(userid);
        if (maybeUser.isEmpty())
            return new ArrayList<Orders>();
        return maybeUser.get().getOrders();
    }

    public Orders findOrderById(UUID orderid) {
        Optional<Orders> maybeOrder = ordersRepository.findById(orderid);
        if (maybeOrder.isEmpty())
            return null;
        return maybeOrder.get();
    }

    public List<Orders> findFulfilledOrders(UUID userid) {
        List<Orders> orders = ordersRepository.findFulfilled(userid);

        return orders;
    }

    public Orders fulfillOrders(UUID orderid) {
        Orders order = findOrderById(orderid);
        order.setFulfilled(true);
        ordersRepository.save(order);

        return order;
    }

}
