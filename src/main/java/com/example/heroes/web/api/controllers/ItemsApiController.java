package com.example.heroes.web.api.controllers;

import com.example.heroes.services.models.items.ItemServiceModel;
import com.example.heroes.services.services.ItemService;
import com.example.heroes.web.api.models.ItemsResponseModel;
import com.example.heroes.web.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public List<ItemsResponseModel> getItems(HttpSession session) {
        String username = getUsername(session);
        return  itemService.getItemsForUser(username)
                .stream()
                .map(item -> modelMapper.map(item, ItemsResponseModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/items/{id}")
    public void BuyItem(@PathVariable long id) {
        int b = 33;
    }

}
