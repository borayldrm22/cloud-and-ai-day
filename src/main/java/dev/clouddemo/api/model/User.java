package dev.clouddemo.api.model;

/**
 * Core user record kept by the demo service.
 */
public class User {

    private final Long id;
    private final String name;
    private final String email;
    private final boolean active;

    public User(Long id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }
}
