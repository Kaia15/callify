package io.callify_spring.UserApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.callify_spring.UserApp.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    // define necessary methods to override from default methods
    User findByEmail(String email);
}
