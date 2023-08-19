package com.kulturman.mdd.controllers;

import com.kulturman.mdd.BaseIntegrationTest;
import com.kulturman.mdd.repositories.ThemeRepository;
import com.kulturman.mdd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ThemeControllerTest extends BaseIntegrationTest {
    @Autowired
    UserService userService;

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getAllThemes() throws Exception {
        mockMvc.perform(authenticatedGet("/api/themes", "itachi@konoha.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(content().json("""
                  [
                  {
                    "id": 1,
                    "name": "Theme 1",
                    "description": "Desc T1"
                  },
                  {
                    "id": 2,
                    "name": "Theme 2",
                    "description": "Desc T2"
                  },
                  {
                    "id": 3,
                    "name": "Theme 3",
                    "description": "Desc T3"
                  }
                ]
                """)
            );
    }

    @Test
    void unsubscribe() throws Exception {
        Long themeId = 2l;
        Long userId = 2l;

        mockMvc.perform(authenticatedPost(
            String.format("/api/themes/%d/unsubscribe", themeId))
        ).andExpect(status().isOk());

        List<Subscription> result = getSubscriptionList(themeId, userId);

        assertThat(result.size()).isZero();
    }

    private List<Subscription> getSubscriptionList(Long themeId, Long userId) {
        var query = String.format(
            "SELECT * FROM subscriptions WHERE user_id = %d AND theme_id = %d",
            userId, themeId
        );

        var result = jdbcTemplate.query(query, (rs, rowNum) -> new Subscription(rs.getLong("user_id"), rs.getLong("theme_id")));
        return result;
    }

    @Test
    void subscribe() throws Exception {
        Long themeId = 1l;
        Long userId = 2l;

        mockMvc.perform(authenticatedPost(
            String.format("/api/themes/%d/subscribe", themeId))
        ).andExpect(status().isOk());

        List<Subscription> result = getSubscriptionList(themeId, userId);

        assertThat(result.size()).isEqualTo(1);
    }
}

record Subscription(long userId, long themeId) {}
interface SubscriptionRepository {
    boolean subscriptionExists(long userId, long themeId);
}
