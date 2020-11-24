package com.example.heroes.data.repositories;

import com.example.heroes.data.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> getByNameIgnoreCase(String name);

}
