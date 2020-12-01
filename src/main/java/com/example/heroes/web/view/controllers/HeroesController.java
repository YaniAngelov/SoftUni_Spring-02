package com.example.heroes.web.view.controllers;

import com.example.heroes.errors.HeroNotFoundException;
import com.example.heroes.services.models.auth.LoginUserServiceModel;
import com.example.heroes.services.models.heroes.HeroCreateServiceModel;
import com.example.heroes.services.models.heroes.HeroDetailsServiceModel;
import com.example.heroes.services.services.AuthenticatedUserService;
import com.example.heroes.services.services.HeroesService;
import com.example.heroes.services.services.UserService;
import com.example.heroes.web.base.BaseController;
import com.example.heroes.web.view.models.HeroCreateModel;
import com.example.heroes.web.view.models.HeroDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/heroes")
public class HeroesController extends BaseController {

    private final HeroesService heroesService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserService authenticatedUserService;


    public HeroesController(HeroesService heroesService, UserService userService, ModelMapper modelMapper,
                            AuthenticatedUserService authenticatedUserService) {
        this.heroesService = heroesService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticatedUserService = authenticatedUserService;
    }

    @GetMapping("/details/{name}")
    public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView) {
        HeroDetailsServiceModel serviceModel = heroesService.getByName(name);
        HeroDetailsViewModel viewModel = modelMapper.map(serviceModel, HeroDetailsViewModel.class);
        modelAndView.addObject("hero", viewModel);
        modelAndView.setViewName("heroes/hero-details.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public String getCreateHeroForm(HttpSession session) {
        return "heroes/create-hero.html";
    }

    @PostMapping("/create")
    public String createHero(@ModelAttribute HeroCreateModel hero, HttpSession session) {

        String username = getUsername(session);

        HeroCreateServiceModel serviceModel = modelMapper.map(hero, HeroCreateServiceModel.class);
        try {
            heroesService.createHeroForUser(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, hero.getName());
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/heroes/details/" + hero.getName();
        } catch (Exception ex) {
            return "redirect:/heroes/create";
        }
    }

    @ExceptionHandler(HeroNotFoundException.class)
    public ModelAndView handleException(HeroNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("custom-error");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

}
