package com.kulturman.mdd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.kulturman.mdd.dtos.requests.LoginRequest;
import com.kulturman.mdd.dtos.requests.RegisterRequest;
import com.kulturman.mdd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@DBUnit(caseSensitiveTableNames = true)
@DataSet(value = "data/users.json")
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void registersSuccessfully() throws Exception {
        mockMvc.perform(
            post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest(
                    "kulturman@gmail.com", "kulturman", "Aa123456@"
                    )
                ))
        ).andExpect(status().isOk());

        var user = userService.findByEmail("kulturman@gmail.com").orElseThrow();
        assertThat(user.getEmail()).isEqualTo("kulturman@gmail.com");
        assertThat(user.username()).isEqualTo("kulturman");
        assertThat(user.getPassword()).isNotNull();
    }

    @Test
    void failsToRegisterIfEmailIsUsed() throws Exception {
        mockMvc.perform(
            post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest(
                    "kakashi@konoha.com", "kulturman", "Ty1970@89"
                    )
                ))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void failsToRegisterIfUsernameIsUsed() throws Exception {
        mockMvc.perform(
            post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest(
                    "test@gmail.com", "itachi", "Ty1970@89"
                    )
                ))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void authenticateSuccessfully() throws Exception {
        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new LoginRequest("itachi@konoha.com", "Aa123456@")))
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$.token").isString());
    }

    @Test
    void authenticationFailsIfEmailOrPasswordIsWrong() throws Exception {
        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new LoginRequest("itachi@konoha.com2", "Aa123456@")))
        ).andExpect(status().isForbidden());
    }
}
