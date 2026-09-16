package dev.clouddemo.api.model;

/**
 * Public-facing projection of {@link User}. Never exposes internal ids.
 */
public class UserProfile {

    private final String displayName;
    private final String email;
    private final String status;

    public UserProfile(String displayName, String email, String status) {
        this.displayName = displayName;
        this.email = email;
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
