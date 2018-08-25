package com.pioneersoft.sportmasterbot.dao;

import com.pioneersoft.sportmasterbot.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDAO extends JpaRepository<Item, String> {

    List<Item> findAllByAvialability(Boolean avialability);
}
