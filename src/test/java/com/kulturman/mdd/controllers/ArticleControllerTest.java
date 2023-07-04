package com.kulturman.mdd.controllers;
import com.kulturman.mdd.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ArticleControllerTest extends BaseIntegrationTest {
    @Test
    void getAllArticlesAsc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=ASC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(2)));

        expectElementToMatch(resultActions, "Article 1", "Article 1 content", "kakashi", "2023-06-29T00:00:00");
    }

    @Test
    void getAllArticlesDesc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=DESC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(2)));

        expectElementToMatch(resultActions,"Article 2", "Article 2 content", "itachi", "2023-06-29T01:00:00");
    }

    private void expectElementToMatch(ResultActions resultActions, String title, String content, String author, String createdAt) throws Exception {
        resultActions.andExpect(jsonPath("$[0].title").value("Article 1"))
            .andExpect(jsonPath("$[0].content").value("Article 1 content"))
            .andExpect(jsonPath("$[0].author").value("kakashi"))
            .andExpect(jsonPath("$[0].createdAt").value("2023-06-29T00:00:00"));
    }
}
