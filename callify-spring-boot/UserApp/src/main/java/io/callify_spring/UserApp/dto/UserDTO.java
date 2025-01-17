package io.callify_spring.UserApp.dto;

import io.callify_spring.UserApp.model.UserType;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String password;
    private UserType planUnitedType;
    private Long id;
    private String email;
    private String displayName;

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public UserType getPlanUnitedType() {
        return this.planUnitedType;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
}
