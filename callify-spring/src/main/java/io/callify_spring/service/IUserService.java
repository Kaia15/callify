package io.callify_spring.service;

import io.callify_spring.dto.UserDTO;
import io.callify_spring.model.User;
import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public User updateUserById(UserDTO userDto);
    public void deleteUserById(Long id);
    public User createUser(String email, String firstName, String lastName, String password);
    public List<User> getUsersByPage(int offset, int pageNum);
}
