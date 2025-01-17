package io.callify_spring.UserApp.service;

import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.callify_spring.UserApp.dto.UserDTO;
import io.callify_spring.UserApp.model.User;
import io.callify_spring.UserApp.model.UserType;
import io.callify_spring.UserApp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getUsersByPage(int offset, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, offset);
        Page<User> page = userRepository.findAll(pageable);
        return page.getContent();
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id) 
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Replace with a custom exception if needed
    };

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    };

    public User updateUser(Long userId, UserDTO userDto) {
        User foundUser = this.getUserById(userId);
        foundUser.modifyUserFromDTO(userDto);
        this.userRepository.save(foundUser);
        return foundUser;
    };

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    };

    public User createUser(String email, String firstName, String lastName, String password) {
        User newUser = new User(email,firstName,lastName,password,UserType.BASIC);
        this.userRepository.save(newUser);
        return newUser;
    };
}
