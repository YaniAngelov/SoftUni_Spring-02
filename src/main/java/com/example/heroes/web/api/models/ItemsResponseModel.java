package com.example.heroes.web.api.models;

import com.example.heroes.data.models.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsResponseModel {

    private long id;
    private String name;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
    private Slot slot;
    private boolean isOwned;

}
