package com.pioneersoft.sportmasterbot.dao;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);
}
