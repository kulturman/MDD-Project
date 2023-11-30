package com.kulturman.mdd.controllers;

import com.kulturman.mdd.BaseIntegrationTest;
import com.kulturman.mdd.dtos.requests.LoginRequest;
import com.kulturman.mdd.dtos.requests.RegisterRequest;
import com.kulturman.mdd.dtos.requests.UpdateProfileRequest;
import com.kulturman.mdd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest extends BaseIntegrationTest {
    @Autowired
    UserService userService;

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

        var user = userService.findByEmailOrUsername("kulturman@gmail.com").orElseThrow();
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
    void authenticateSuccessfullyWithEmail() throws Exception {
        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new LoginRequest("itachi@konoha.com", "Aa123456@")))
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$.token").isString());
    }

    @Test
    void authenticateSuccessfullyWithUsername() throws Exception {
        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new LoginRequest("itachi", "Aa123456@")))
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

    @Test
    void getUserProfile() throws Exception {
        mockMvc.perform(authenticatedGet("/api/auth/me", "kakashi@konoha.com"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                {
                    username: "kakashi",
                    email: "kakashi@konoha.com",
                    subscriptions: [
                        {
                            id: 1,
                            title: "Theme 1",
                            description: "Desc T1"
                        },
                        {
                            id: 2,
                            title: "Theme 2",
                            description: "Desc T2"
                        }
                    ]
                }
            """));
    }

    @Test
    void updatingProfileFailsWhenUsernameOrEmailIsAlreadyUsed() throws Exception {
        final var currentUserEmail = "itachi@konoha.com";

        mockMvc.perform(
            authenticatedPost("/api/auth/me/update", currentUserEmail)
                .content(objectMapper.writeValueAsString(new UpdateProfileRequest("kakashi@konoha.com", "kakashi")))
        ).andExpect(status().isBadRequest());

        var updatedUser = userService.findByEmailOrUsername(currentUserEmail).orElseThrow();
        assertThat(updatedUser.getUsername()).isEqualTo("itachi@konoha.com");
    }

    @Test
    void updatesProfileSuccessfullyWhenUsernameAndEmailAreFree() throws Exception {
        final var updateProfileRequest = new UpdateProfileRequest("sasuke@konoha.com", "sasuke");

        mockMvc.perform(
            authenticatedPost("/api/auth/me/update", "itachi@konoha.com")
                .content(objectMapper.writeValueAsString(updateProfileRequest))
        ).andExpect(status().isOk());

        var updatedUser = userService.findByEmailOrUsername(updateProfileRequest.email()).orElseThrow();
        assertThat(updatedUser.getEmail()).isEqualTo(updateProfileRequest.email());
        assertThat(updatedUser.username()).isEqualTo(updateProfileRequest.username());
    }
}
