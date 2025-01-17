package io.callify_spring.UserApp.service;

import java.util.List;

import io.callify_spring.UserApp.dto.UserDTO;
import io.callify_spring.UserApp.model.User;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public User updateUser(Long userId, UserDTO userDto);
    public void deleteUserById(Long id);
    public User createUser(String email, String firstName, String lastName, String password);
    public List<User> getUsersByPage(int offset, int pageNum);
}
