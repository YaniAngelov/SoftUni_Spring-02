package com.example.heroes.web.api.controllers;

import com.example.heroes.services.models.items.ItemCreateServiceModel;
import com.example.heroes.services.services.ItemService;
import com.example.heroes.web.api.models.ItemCreateRequestModel;
import com.example.heroes.web.api.models.ItemsResponseModel;
import com.example.heroes.web.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemsApiController extends BaseController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public ItemsApiController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/api/items")
    public ResponseEntity<List<ItemsResponseModel>> getItems(HttpSession session) {
        String username = getUsername(session);
        List<ItemsResponseModel> result = itemService.getItemsForUser(username)
                .stream()
                .map(item -> modelMapper.map(item, ItemsResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/api/items")
    public void create(ItemCreateRequestModel requestModel, HttpServletResponse response) throws IOException {
        ItemCreateServiceModel serviceModel = modelMapper.map(requestModel, ItemCreateServiceModel.class);
        itemService.create(serviceModel);

        response.sendRedirect("/items/merchant");
    }

    @PostMapping("/api/items/add-to-user/{id}")
    public void BuyItem(@PathVariable long id, HttpSession session, HttpServletResponse response) throws IOException {
        String username = getUsername(session);
        itemService.addToUserById(id, username);
        String heroName = getHeroName(session);

        response.sendRedirect("/heroes/details/" + heroName);
    }

}
