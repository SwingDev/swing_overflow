package io.swingdev.swing_overflow.user_management.domain;

public class UserDetails {
    private String id;
    private String name;

    private UserDetails() { }

    public UserDetails(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
