package com.unicomapi.controller;

import com.unicomapi.annotation.CurrentUser;
import com.unicomapi.model.User;
import com.unicomapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@CurrentUser User user,  @PathVariable Long id) {
        return userService.getUser(id);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@CurrentUser User user, @PathVariable Long id, @RequestBody User userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@CurrentUser User user, @PathVariable Long id) {
        userService.deleteUser(id);
    }

}
