package com.kulturman.mdd.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kulturman.mdd.BaseIntegrationTest;
import com.kulturman.mdd.dtos.requests.CreateArticleRequest;
import com.kulturman.mdd.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerTest extends BaseIntegrationTest {
    @Autowired
    ArticleRepository articleRepository;
    @Test
    void getAllArticlesAsc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=ASC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(2)));

        expectElementToMatch(resultActions, 1, "Article 1", "Article 1 content", "kakashi", "2023-06-29T00:00:00");
    }

    @Test
    void getAllArticlesDesc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=DESC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(2)));

        expectElementToMatch(resultActions,2, "Article 2", "Article 2 content", "itachi", "2023-06-29T01:00:00");
    }

    @Test
    void createArticle() throws Exception {
        var articleToCreate = new CreateArticleRequest(1l, "Just created article", "Test article content");

        mockMvc.perform(authenticatedPost("/api/articles", "itachi@konoha.com")
            .content(objectMapper.writeValueAsString(articleToCreate)))
            .andExpect(status().is(201));

        var article = articleRepository.findByTitle(articleToCreate.title()).orElseThrow();

        assertThat(article.getContent()).isEqualTo(articleToCreate.content());
        assertThat(article.getAuthor().getEmail()).isEqualTo("itachi@konoha.com");
        assertThat(article.getTheme().getId()).isEqualTo(articleToCreate.themeId());
    }

    private void expectElementToMatch(ResultActions resultActions, long id, String title, String content, String author, String createdAt) throws Exception {
        resultActions.andExpect(jsonPath("$[0].title").value("Article 1"))
            .andExpect(jsonPath("$[0].content").value("Article 1 content"))
            .andExpect(jsonPath("$[0].author").value("kakashi"))
            .andExpect(jsonPath("$[0].createdAt").value("2023-06-29T00:00:00"))
            .andExpect(jsonPath("$[0].id").value(id));
    }
}
