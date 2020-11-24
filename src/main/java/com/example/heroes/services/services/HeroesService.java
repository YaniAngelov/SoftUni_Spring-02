package com.example.heroes.services.services;

import com.example.heroes.data.models.Hero;
import com.example.heroes.services.models.heroes.HeroCreateServiceModel;
import com.example.heroes.services.models.heroes.HeroDetailsServiceModel;

public interface HeroesService {

    HeroDetailsServiceModel getByName(String name);

    Hero create(HeroCreateServiceModel serviceModel);

    void createHeroForUser(String username, HeroCreateServiceModel hero) throws Exception;

}
