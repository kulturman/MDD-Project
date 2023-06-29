package com.kulturman.mdd.controllers;
import com.kulturman.mdd.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ArticleControllerTest extends BaseIntegrationTest {
    @Test
    void getAllArticles() throws Exception {
        mockMvc.perform(
            authenticatedGet("/api/articles", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].title").value("Article 1"))
            .andExpect(jsonPath("$[0].content").value("Article 1 content"))
            .andExpect(jsonPath("$[0].author").value("kakashi"))
            .andExpect(jsonPath("$[0].createdAt").value("2023-06-29T00:00:00")).andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[1].title").value("Article 2"))
            .andExpect(jsonPath("$[1].content").value("Article 2 content"))
            .andExpect(jsonPath("$[1].author").value("itachi"))
            .andExpect(jsonPath("$[1].createdAt").value("2023-06-29T01:00:00"));
    }
}
