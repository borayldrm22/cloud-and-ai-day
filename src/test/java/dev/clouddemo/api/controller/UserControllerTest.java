package dev.clouddemo.api.controller;

import dev.clouddemo.api.repository.UserRepository;
import dev.clouddemo.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        UserService service = new UserService(new UserRepository());
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(service)).build();
    }

    @Test
    void list_returnsAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void stats_reportsActiveCount() throws Exception {
        mockMvc.perform(get("/api/users/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(2));
    }

    @Test
    void profile_returnsProjectionForKnownUser() throws Exception {
        mockMvc.perform(get("/api/users/1/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.displayName").value("Bora Yildirim"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void profile_returns404ForUnknownUser() throws Exception {
        mockMvc.perform(get("/api/users/999/profile"))
                .andExpect(status().isNotFound());
    }
}
