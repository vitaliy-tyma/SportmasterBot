package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.Item;
import com.pioneersoft.sportmasterbot.model.Shop;
import org.jsoup.nodes.Document;

public interface OrderSerrvice
{
    Document orderItemInShop(Item item, Shop shop);
}
