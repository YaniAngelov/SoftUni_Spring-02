package com.example.heroes.services.services;

import com.example.heroes.services.models.items.ItemCreateServiceModel;
import com.example.heroes.services.models.items.ItemServiceModel;

import java.util.List;

public interface ItemService {

    List<ItemServiceModel> getItemsForUser(String username);

    void create(ItemCreateServiceModel serviceModel);

    void addToUserById(long id, String username);
}
