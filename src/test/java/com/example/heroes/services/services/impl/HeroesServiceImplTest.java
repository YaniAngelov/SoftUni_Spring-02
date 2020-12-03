package com.example.heroes.services.services.impl;

import com.example.heroes.data.repositories.HeroesRepository;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.factories.HeroesFactory;
import com.example.heroes.services.factories.impl.HeroesFactoryImpl;
import com.example.heroes.services.services.UserService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class HeroesServiceImplTest {

    HeroesRepository heroesRepository;
    HeroesFactory heroesFactory;
    UserService userService;
    UserRepository userRepository;

    @InjectMocks
    HeroesServiceImpl service;

    @Before
    public void setupTest() {

        heroesRepository = Mockito.mock(HeroesRepository.class);
        heroesFactory = new HeroesFactoryImpl();
        userRepository = Mockito.mock(UserRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper);
        service = new HeroesServiceImpl(heroesRepository, modelMapper, heroesFactory, userService);
    }

    // 1) user exists and does NOT have a hero
    // 2) user does NOT exist
    // 3) user exists and has a hero

//    @Test
//    public void createHeroForUser_whenUserExistsAndDoesNotHaveAHero_shouldCreateHeroForUser() throws Exception {
//
//        // Arrange
//        User user = new User();
//        user.setUsername("Yani");
//        String heroName = "Paladin";
//        Mockito.when(userRepository.findByUsername(user.getUsername()))
//                .thenReturn(user);
//        Mockito.when(userService.getUsername(user.getUsername()))
//                .thenReturn(user);
//
//        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel(heroName, Gender.MALE);
//
//        // Act
//        service.createHeroForUser(user.getUsername(), heroToCreate);
//
//        // Assert
//        assertEquals(heroName, user.getHero().getName());
//
//    }

    public void createHeroForUser_whenUserDoesNotExist_shouldThrowException() {

    }

    public void createHeroForUser_whenUserExistsAndHasAHero_shouldThrowException() {

    }

}