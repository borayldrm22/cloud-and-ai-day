package dev.clouddemo.api.repository;

import dev.clouddemo.api.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory store. Stands in for a real database in the demo.
 *
 * <p>Note: {@link #findById(Long)} returns {@code null} for unknown ids,
 * matching the legacy DAO this service was ported from.
 */
@Repository
public class UserRepository {

    private final Map<Long, User> users = new LinkedHashMap<>();

    public UserRepository() {
        save(new User(1L, "Bora Yildirim", "bora@example.com", true));
        save(new User(2L, "Ada Lovelace", "ada@example.com", true));
        save(new User(3L, "Alan Turing", "alan@example.com", false));
    }

    public void save(User user) {
        users.put(user.getId(), user);
    }

    /**
     * @return the user, or {@code null} when no user carries that id
     */
    public User findById(Long id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public long countActive() {
        return users.values().stream().filter(User::isActive).count();
    }
}
