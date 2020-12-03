package com.example.heroes.services.services.validations;

import com.example.heroes.services.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {

    boolean isValid(RegisterUserServiceModel user);

}
