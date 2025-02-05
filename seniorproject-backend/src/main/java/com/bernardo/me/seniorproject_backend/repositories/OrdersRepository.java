package com.bernardo.me.seniorproject_backend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bernardo.me.seniorproject_backend.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    @Query("select o from Orders o where o.fulfilled=true and o.student.userid=:user")
    List<Orders> findFulfilled(UUID user);

}
