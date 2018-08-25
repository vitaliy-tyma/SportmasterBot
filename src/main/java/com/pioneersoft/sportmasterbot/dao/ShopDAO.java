package com.pioneersoft.sportmasterbot.dao;

import com.pioneersoft.sportmasterbot.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDAO extends JpaRepository<Shop, String> {
}
