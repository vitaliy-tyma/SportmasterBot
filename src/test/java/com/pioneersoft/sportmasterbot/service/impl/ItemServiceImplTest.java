package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemServiceImplTest {

    @Ignore
    @Test
    public void findItemByItemId() {
        ItemServiceImpl itemsService = new ItemServiceImpl();
        Item item = itemsService.findItemByItemId("ESS025DA38");

        assertNotNull(item);
        assertEquals("ESS025DA38", item.getItemId());

//        System.out.println(item);
    }
}