package io.callify_spring.UserApp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;

import io.callify_spring.UserApp.dto.UserDTO;
import io.callify_spring.UserApp.model.User;
import io.callify_spring.UserApp.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/users") // get all users with & without pagination
    public List<User> getAllUsers(
        @RequestParam(required = false) Integer offset,
        @RequestParam(required = false) Integer pageNum) {
        if (offset != null && pageNum != null) {
            return userService.getUsersByPage(offset, pageNum);
        }
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody UserDTO userDto) {
        return this.userService.createUser(userDto.getEmail(),userDto.getFirstName(),userDto.getLastName(),userDto.getPassword());
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }

    @PatchMapping("/users")
    public User getUserById(@Valid @RequestBody UserDTO userDto) {
        return this.userService.updateUser(userDto);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);;
    }

}

