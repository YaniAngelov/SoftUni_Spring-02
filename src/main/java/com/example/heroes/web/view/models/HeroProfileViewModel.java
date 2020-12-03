package com.example.heroes.web.view.models;

import com.example.heroes.data.models.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroProfileViewModel {

    private String name;
    private int level;
    private Gender gender;

}
