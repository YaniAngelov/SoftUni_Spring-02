package com.example.heroes.services.factories;

import com.example.heroes.data.models.Gender;
import com.example.heroes.data.models.Hero;

public interface HeroesFactory {

    Hero create(String name, Gender gender);
}
