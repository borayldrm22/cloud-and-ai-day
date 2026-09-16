package dev.clouddemo.api.service;

import dev.clouddemo.api.model.User;
import dev.clouddemo.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(new UserRepository());
    }

    @Test
    void findById_returnsSeededUser() {
        User user = service.findById(1L);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Bora Yildirim");
    }

    @Test
    void findById_returnsNullForUnknownId() {
        assertThat(service.findById(999L)).isNull();
    }

    @Test
    void listUsers_returnsEverySeededUser() {
        assertThat(service.listUsers()).hasSize(3);
    }

    @Test
    void activeUserCount_ignoresSuspendedUsers() {
        assertThat(service.activeUserCount()).isEqualTo(2);
    }

    @Test
    void summary_describesTheUserTable() {
        assertThat(service.summary()).isEqualTo("3 users, 2 active");
    }
}
