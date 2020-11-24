package com.example.heroes.services.factories.impl;

import com.example.heroes.services.factories.HeroesConstants;
import com.example.heroes.data.models.Gender;
import com.example.heroes.data.models.Hero;
import com.example.heroes.services.factories.HeroesFactory;
import org.springframework.stereotype.Component;

@Component
public class HeroesFactoryImpl implements HeroesFactory {

    @Override
    public Hero create(String name, Gender gender) {

        Hero hero = new Hero();
        hero.setName(name);
        hero.setGender(gender);
        hero.setAttack(HeroesConstants.INITIAL_ATTACK);
        hero.setDefence(HeroesConstants.INITIAL_DEFENCE);
        hero.setLevel(HeroesConstants.INITIAL_LEVEL);
        hero.setStamina(HeroesConstants.INITIAL_STAMINA);
        hero.setStrength(HeroesConstants.INITIAL_STRENGTH);

        return hero;
    }
}
