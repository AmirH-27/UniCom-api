package com.unicomapi.service;

import com.unicomapi.model.User;
import com.unicomapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
