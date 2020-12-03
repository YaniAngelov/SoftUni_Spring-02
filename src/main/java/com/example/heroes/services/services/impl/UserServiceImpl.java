package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.User;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


}
