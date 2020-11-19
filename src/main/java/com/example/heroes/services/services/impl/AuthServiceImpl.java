package com.example.heroes.services.services.impl;

import com.example.heroes.services.models.auth.RegisterUserServiceModel;
import com.example.heroes.services.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void register(RegisterUserServiceModel model) {
        // validate email, password match..
        // create user or throw exception

        System.out.println();
    }
}
