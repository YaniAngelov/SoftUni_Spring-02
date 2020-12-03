package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.Hero;
import com.example.heroes.data.models.Item;
import com.example.heroes.data.models.Slot;
import com.example.heroes.data.models.User;
import com.example.heroes.data.repositories.HeroesRepository;
import com.example.heroes.data.repositories.ItemsRepository;
import com.example.heroes.services.models.items.ItemServiceModel;
import com.example.heroes.services.services.validations.ItemsValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {
    List<Item> items;


    ItemServiceImpl service;
    ItemsRepository itemsRepository;
    ItemsValidationService itemsValidationService;
    HeroesRepository heroesRepository;


    @BeforeEach
    public void setupTest() {

        items = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        itemsRepository = Mockito.mock(ItemsRepository.class);
        itemsValidationService = Mockito.mock(ItemsValidationService.class);
        heroesRepository =  Mockito.mock(HeroesRepository.class);
        Mockito.when(itemsRepository.findAll())
                .thenReturn(items);

        service = new ItemServiceImpl(itemsRepository, modelMapper, itemsValidationService, heroesRepository);
    }

    @Test
    public void getItemsForUser_whenNoItems_shouldReturnEmptyList() {
        items.clear();

        List<ItemServiceModel> actualItems = service.getItemsForUser("username");

        assertEquals(0, actualItems.size());
    }

    @Test
    public void getItemsForUser_whenItemsButNotForUser_shouldReturnItemsWithoutOwned() {
        items.clear();
        items.addAll(getItems());

        List<ItemServiceModel> actualItems = service.getItemsForUser("username");

        assertEquals(items.size(), actualItems.size());
        boolean hasNoOwned = actualItems.stream()
                .filter(ItemServiceModel::isOwned)
                .findAny()
                .isEmpty();

        assertTrue(hasNoOwned);
    }

    @Test
    public void getItemsForUser_whenItemsAndOneForUser_shouldReturnItemsWithOneForUser() {
        items.clear();
        items.addAll(getItems());

        Hero hero = new Hero();
        User user = new User();
        user.setUsername("username");
        hero.setUser(user);

        items.get(0)
                .setHeroes(new ArrayList<>());
        items.get(0)
                .getHeroes()
                .add(hero);

        List<ItemServiceModel> actualItems = service.getItemsForUser(user.getUsername());

        assertEquals(items.size(), actualItems.size());

        List<ItemServiceModel> itemsForUser = actualItems.stream()
                .filter(ItemServiceModel::isOwned)
                .collect(Collectors.toList());

        assertEquals(1, itemsForUser.size());
        assertEquals(items.get(0).getId(), itemsForUser.get(0).getId());
    }

    private List<Item> getItems() {
        return List.of(
                new Item() {{
                    setId(1);
                    setName("Item 1");
                    setSlot(Slot.PADS);
                    setStamina(1);
                    setAttack(2);
                    setDefence(3);
                    setStrength(4);
                }},
                new Item() {{
                    setId(2);
                    setName("Item 2");
                    setSlot(Slot.PADS);
                    setStamina(5);
                    setAttack(6);
                    setDefence(7);
                    setStrength(8);
                }}
        );
    }


}