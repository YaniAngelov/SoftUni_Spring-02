package com.example.heroes.services.services.impl;

import com.example.heroes.data.models.User;
import com.example.heroes.data.repositories.UserRepository;
import com.example.heroes.services.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}
