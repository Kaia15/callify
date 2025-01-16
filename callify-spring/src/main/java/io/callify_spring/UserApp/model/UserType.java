package io.callify_spring.UserApp.model;

public enum UserType {
    BASIC(1), LICENSED(2), GUEST(4), NONE(99);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int value() {return value;}

}
