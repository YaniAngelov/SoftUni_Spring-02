package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.Hero;
import com.example.heroes.data.models.User;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.models.heroes.HeroCreateServiceModel;
import com.example.heroes.services.services.HeroesService;
import com.example.heroes.services.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HeroesService heroesService;

    public UserServiceImpl(UserRepository userRepository, HeroesService heroesService) {
        this.userRepository = userRepository;
        this.heroesService = heroesService;
    }



//    @Override
//    public void createHeroForUser(String username, HeroCreateServiceModel heroServiceModel) throws Exception {
//        User user = userRepository.findByUsername(username);
//
//        if (user.getHero() != null) {
//            throw new Exception("User already has a hero");
//        }
//
//        Hero hero = heroesService.create(heroServiceModel);
//        user.setHero(hero);
//
//        userRepository.save(user);
//    }
}
