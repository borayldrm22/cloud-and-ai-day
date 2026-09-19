package dev.clouddemo.api.service;

import dev.clouddemo.api.model.User;
import dev.clouddemo.api.model.UserProfile;
import dev.clouddemo.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic around users.
 *
 * <p>This service is deliberately thin: it wraps the repository and adds
 * the projection logic used by the public API. Anything that talks to the
 * outside world (HTTP status codes, serialization) belongs in the
 * controller layer instead.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Looks a user up by id.
     *
     * @param id the user id
     * @return the user, or {@code null} when nothing matches
     */
    public User findById(Long id) {
        log.debug("Looking up user id={}", id);
        return repository.findById(id);
    }

    /**
     * @return every user currently known to the service
     */
    public List<User> listUsers() {
        return repository.findAll();
    }

    /**
     * @return how many users are currently marked active
     */
    public long activeUserCount() {
        return repository.countActive();
    }

    /**
     * Convenience helper used by the health endpoint.
     *
     * @return a one-line summary of the current user table
     */
    public String summary() {
        return String.format(
                "%d users, %d active",
                listUsers().size(),
                activeUserCount());
    }

    /**
     * Builds the public projection for a resolved user.
     *
     * <p>The caller is responsible for resolving the user first: this method
     * assumes a real user instance and maps it onto the wire format the API
     * consumers expect. Suspended users are still projected — the status
     * field carries that information, rather than the endpoint failing.
     *
     * @param user the resolved user
     * @return the public profile projection
     * @see UserProfile
     */
    public UserProfile buildProfile(User user) {
        log.debug("Building profile projection");

        // status is derived, never stored
        String status = user.isActive() ? "ACTIVE" : "SUSPENDED";

        return new UserProfile(
                user.getName(),
                user.getEmail(),
                status);
    }
}
