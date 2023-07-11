package com.kulturman.mdd.controllers;

import com.kulturman.mdd.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ThemeControllerTest extends BaseIntegrationTest {
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
}
