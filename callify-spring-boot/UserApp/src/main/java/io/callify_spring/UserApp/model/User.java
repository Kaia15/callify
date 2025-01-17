package io.callify_spring.UserApp.model;

import io.callify_spring.UserApp.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String displayName;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_united_type", nullable = false)
    private UserType planUnitedType;

    // Default constructor
    public User() {
    }

    // Full constructor
    public User(String email, String firstName, String lastName, String password, UserType planUnitedType) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.planUnitedType = planUnitedType;
    }

    // Getters and Setters (not shown for brevity)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public void modifyUserFromDTO(UserDTO userDto) {
        if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty()) {
            this.firstName = userDto.getFirstName();
        }
        if (userDto.getLastName() != null && !userDto.getLastName().isEmpty()) {
            this.lastName = userDto.getLastName();
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            this.password = userDto.getPassword();
        }
        if (userDto.getDisplayName() != null && !userDto.getDisplayName().isEmpty()) {
            this.displayName = userDto.getDisplayName();
        }
        if (userDto.getPlanUnitedType() != null) {
            this.planUnitedType = userDto.getPlanUnitedType();
        }
    }

    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public UserType getPlanUnitedType() {
        return planUnitedType;
    }
    
}
