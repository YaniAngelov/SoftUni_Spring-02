package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.Hero;
import com.example.heroes.data.models.Item;
import com.example.heroes.data.repositories.HeroesRepository;
import com.example.heroes.data.repositories.ItemsRepository;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.models.items.ItemCreateServiceModel;
import com.example.heroes.services.models.items.ItemServiceModel;
import com.example.heroes.services.services.ItemService;
import com.example.heroes.services.services.validations.ItemsValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper;
    private final ItemsValidationService itemsValidationService;
    private final HeroesRepository heroesRepository;

    public ItemServiceImpl(ItemsRepository itemsRepository, ModelMapper modelMapper,
                           ItemsValidationService itemsValidationService, HeroesRepository heroesRepository) {
        this.itemsRepository = itemsRepository;
        this.modelMapper = modelMapper;
        this.itemsValidationService = itemsValidationService;
        this.heroesRepository = heroesRepository;
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

    @Override
    public void create(ItemCreateServiceModel serviceModel) {
        if (!this.itemsValidationService.isValid(serviceModel)) {
            throw new RuntimeException("Hero is invalid");
        }

        Item item = modelMapper.map(serviceModel, Item.class);
        itemsRepository.save(item);
    }

    @Override
    public void addToUserById(long id, String username) {
        Optional<Hero> heroResult = heroesRepository.getByUserUsername(username);
        if (heroResult.isEmpty()) {
            throw new NullPointerException("User does not have a hero");
        }

        Optional<Item> itemResult = itemsRepository.findById(id);
        if (itemResult.isEmpty()) {
            throw new NullPointerException("Item does not exists");
        }


        Hero hero = heroResult.get();
        Item item = itemResult.get();

        boolean hasItem = false;
        for (Item currItem: hero.getItems() ) {
            if (currItem.getSlot() == item.getSlot()) {
                hasItem = true;
                break;
            }
        }

        if (!hasItem) {
            hero.getItems().add(item);
            hero.setStrength(hero.getStrength() + item.getStrength());
            hero.setStamina(hero.getStamina() + item.getStamina());
            hero.setAttack(hero.getAttack() + item.getAttack());
            hero.setDefence(hero.getDefence() + item.getDefence());

            heroesRepository.saveAndFlush(hero);
        }
    }
}
