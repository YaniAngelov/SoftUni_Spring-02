package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.*;
import com.example.heroes.data.repositories.HeroesRepository;
import com.example.heroes.errors.HeroNotFoundException;
import com.example.heroes.services.factories.HeroesFactory;
import com.example.heroes.services.models.heroes.HeroCreateServiceModel;
import com.example.heroes.services.models.heroes.HeroDetailsServiceModel;
import com.example.heroes.services.models.heroes.HeroItemServiceModel;
import com.example.heroes.services.models.heroes.HeroProfileServiceModel;
import com.example.heroes.services.services.HeroesService;
import com.example.heroes.services.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroesServiceImpl implements HeroesService {

    private final HeroesRepository heroesRepository;
    private final ModelMapper modelMapper;
    private final HeroesFactory heroesFactory;
    private final UserService userService;

    public HeroesServiceImpl(HeroesRepository heroesRepository, ModelMapper modelMapper, HeroesFactory heroesFactory,
                             UserService userService) {
        this.heroesRepository = heroesRepository;
        this.modelMapper = modelMapper;
        this.heroesFactory = heroesFactory;
        this.userService = userService;
    }

    @Override
    public HeroDetailsServiceModel getByName(String name) {

        Hero hero = heroesRepository.getByNameIgnoreCase(name)
                .orElseThrow(() -> new HeroNotFoundException("Hero with such name does not exist"));

        //        Optional<Hero> heroResult = heroesRepository.getByNameIgnoreCase(name);
//        if (heroResult.isEmpty()) {
//            throw new HeroNotFoundException("Hero with such name does not exist");
//        }

//        Hero hero = heroResult.get();

        HeroDetailsServiceModel serviceModel = modelMapper.map(hero, HeroDetailsServiceModel.class);

        serviceModel.setWeapon(getItemBySlot(hero.getItems(), Slot.WEAPON));
        serviceModel.setGauntlets(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        serviceModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        serviceModel.setPads(getItemBySlot(hero.getItems(), Slot.PADS));
        serviceModel.setPauldrons(getItemBySlot(hero.getItems(), Slot.PAULDRON));

        return serviceModel;
    }

    @Override
    public HeroProfileServiceModel getProfileByName(String name) {

        Hero hero = heroesRepository.getByNameIgnoreCase(name)
                .orElseThrow(() -> new HeroNotFoundException("Hero with such name does not exist"));

        HeroProfileServiceModel serviceModel = modelMapper.map(hero, HeroProfileServiceModel.class);

        return serviceModel;
    }

    @Override
    public Hero create(HeroCreateServiceModel serviceModel) {
        Hero hero = heroesFactory.create(serviceModel.getName(), serviceModel.getGender());
        heroesRepository.save(hero);
        return hero;
    }

    @Override
    public void createHeroForUser(String username, HeroCreateServiceModel heroServiceModel) throws Exception {
        User user = userService.getUsername(username);

        if (user.getHero() != null) {
            throw new Exception("User already has a hero");
        }

        Hero hero = create(heroServiceModel);
        hero.setUser(user);

        heroesRepository.saveAndFlush(hero);
    }

    private HeroItemServiceModel getItemBySlot(List<Item> items, Slot slot) {
        Optional<Item> item = items
                .stream()
                .filter(x -> x.getSlot() == slot)
                .findFirst();

        return item.isPresent()
                ? modelMapper.map(item, HeroItemServiceModel.class)
                : null;
    }
}
