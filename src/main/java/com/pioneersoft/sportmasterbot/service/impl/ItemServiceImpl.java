package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.dao.ItemDAO;
import com.pioneersoft.sportmasterbot.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl
{
    @Autowired
    ItemDAO itemDAO;

    public Item save(Item item) {
        if (itemDAO.findOne(item.getItemId()) == null)
        {
            return itemDAO.save(item);
        }
        return null;
    }

    public Item update(Item item) {
        if (itemDAO.findOne(item.getItemId()) != null)
        {
            return itemDAO.save(item);
        }
        return null;
    }

    public Item findOne(String itemId) {
        return itemDAO.findOne(itemId);
    }

    public  void delete(String itemId){
        itemDAO.delete(itemId);
    }

    public List<Item> findAllByAvialability(Boolean avialability){
        return itemDAO.findAllByAvialability(avialability);
    }
}
