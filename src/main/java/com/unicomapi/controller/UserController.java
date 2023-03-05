package com.unicomapi.controller;

import com.unicomapi.annotation.CurrentUser;
import com.unicomapi.auth.AuthenticationResponse;
import com.unicomapi.model.User;
import com.unicomapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
