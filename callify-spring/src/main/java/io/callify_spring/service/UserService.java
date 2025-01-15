package io.callify_spring.service;

import io.callify_spring.model.User;
import io.callify_spring.model.UserType;
import io.callify_spring.repository.UserRepository;
import io.callify_spring.dto.UserDTO;

import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserService implements IUserService {

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

    public User updateUser(UserDTO userDto) {
        User foundUser = this.getUserById(userDto.getId());
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
