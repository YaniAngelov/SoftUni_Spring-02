package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.User;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.models.auth.LoginUserServiceModel;
import com.example.heroes.services.models.auth.RegisterUserServiceModel;
import com.example.heroes.services.services.AuthService;
import com.example.heroes.services.services.validations.AuthValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(AuthValidationService authValidationService, UserRepository userRepository, ModelMapper modelMapper) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!authValidationService.isValid(model)) {
            // do something
            return;
        }

        User user = modelMapper.map(model, User.class);
        userRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(
                serviceModel.getUsername(), serviceModel.getPassword());
        if (userOptional.isEmpty()) {
            throw new Exception("Invalid user");
        }

        User user = userOptional.get();

        String heroName = user.getHero() == null
                ? null
                : user.getHero().getName();

        return new LoginUserServiceModel(serviceModel.getUsername(), heroName);
    }

}
