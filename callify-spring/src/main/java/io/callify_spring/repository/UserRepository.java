package io.callify_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.callify_spring.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    // define necessary methods to override from default methods
    User findByEmail(String email);
}
