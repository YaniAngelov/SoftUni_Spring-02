package com.example.heroes.web.view.controllers;

import com.example.heroes.services.models.heroes.HeroProfileServiceModel;
import com.example.heroes.services.services.HeroesService;
import com.example.heroes.services.services.UserService;
import com.example.heroes.web.base.BaseController;
import com.example.heroes.web.view.models.HeroProfileViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

    private final HeroesService heroesService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UsersController(HeroesService heroesService, UserService userService, ModelMapper modelMapper) {
        this.heroesService = heroesService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile/{name}")
    public ModelAndView getProfile(@PathVariable String name, ModelAndView modelAndView, HttpSession session) {


        HeroProfileServiceModel heroServiceModel = heroesService.getProfileByName(name);
        HeroProfileViewModel heroViewModel = modelMapper.map(heroServiceModel, HeroProfileViewModel.class);

        modelAndView.addObject("hero", heroViewModel);

        modelAndView.setViewName("users/profile.html");
        return modelAndView;

    }
}
