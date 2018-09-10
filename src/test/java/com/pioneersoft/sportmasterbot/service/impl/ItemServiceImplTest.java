package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemServiceImplTest {

    @Test
    public void findItemByItemId() {
        ItemServiceImpl itemsService = new ItemServiceImpl();
        Item item = itemsService.findItemByItemId("ESS025DA38");

        assertNotNull(item);
        assertEquals("S17AO1S454", item.getItemId());

        System.out.println(item);
    }
}