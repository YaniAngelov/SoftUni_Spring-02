package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.Hero;
import com.example.heroes.data.models.Item;
import com.example.heroes.data.repositories.ItemsRepository;
import com.example.heroes.services.models.items.ItemServiceModel;
import com.example.heroes.services.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemsRepository itemsRepository, ModelMapper modelMapper) {
        this.itemsRepository = itemsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ItemServiceModel> getItemsForUser(String username) {
       return itemsRepository.findAll()
                .stream()
                .map(item -> {
                    ItemServiceModel serviceModel = modelMapper.map(item, ItemServiceModel.class);
                    if (item.getHeroes() != null) {
                        Hero hero = item.getHeroes()
                                .stream()
                                .filter(h -> h.getUser().getUsername().equals(username))
                                .findAny()
                                .orElse(null);

                        serviceModel.setOwned(hero != null);
                    }
                    return serviceModel;
                })
                .collect(Collectors.toList());


    }
}
