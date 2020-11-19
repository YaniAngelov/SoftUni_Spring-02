package com.example.heroes.services.services;

import com.example.heroes.services.models.auth.LoginUserServiceModel;
import com.example.heroes.services.models.auth.RegisterUserServiceModel;

public interface AuthService {

    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}
