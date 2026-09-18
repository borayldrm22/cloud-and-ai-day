package dev.clouddemo.api.controller;

import dev.clouddemo.api.model.User;
import dev.clouddemo.api.model.UserProfile;
import dev.clouddemo.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfile> profile(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.buildProfile(user));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> stats() {
        return ResponseEntity.ok(Map.of(
                "total", userService.listUsers().size(),
                "active", userService.activeUserCount(),
                "summary", userService.summary()));
    }
}
