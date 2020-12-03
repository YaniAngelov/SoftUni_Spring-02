package com.example.heroes.services.models.heroes;

import com.example.heroes.data.models.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroProfileServiceModel {

    private String name;
    private int level;
    private Gender gender;

}
