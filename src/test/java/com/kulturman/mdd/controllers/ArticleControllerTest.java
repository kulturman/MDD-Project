package com.kulturman.mdd.controllers;

import com.kulturman.mdd.BaseIntegrationTest;
import com.kulturman.mdd.dtos.requests.CreateArticleRequest;
import com.kulturman.mdd.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ArticleControllerTest extends BaseIntegrationTest {
    @Autowired
    ArticleRepository articleRepository;
    @Test
    void getAllArticlesAsc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=ASC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(3)));

        expectElementToMatch(resultActions, 1, "Article 1", "Article 1 content", "kakashi", "2023-06-29T00:00:00");
    }

    @Test
    void getAllArticlesDesc() throws Exception {
        var resultActions = mockMvc.perform(
            authenticatedGet("/api/articles?sort=DESC", "itachi@konoha.com"))
            .andExpect(jsonPath("$", hasSize(3)));
        System.out.println(resultActions);
        expectElementToMatch(resultActions,3, "Article 3", "Article 3 content", "itachi", "2023-07-10T14:46:40");
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

    @Test
    void getArticle() throws Exception {
        long articleToGetId = 1;

        mockMvc.perform(authenticatedGet("/api/articles/" + articleToGetId))
            .andExpect(status().isOk())
            .andExpect(content().json("""
            {
                "id": 1,
                "title": "Article 1",
                "content": "Article 1 content",
                "createdAt": "2023-06-29T00:00:00",
                "theme": {
                    "id": 1,
                    "name": "Theme 1"
                },
                "author": {
                    "id": 1,
                    "username": "kakashi"
                },
                "comments": [
                    {
                        "id": 2,
                        "content": "Comment 2",
                        "author": {
                            "id": 1,
                            "username": "kakashi"
                        }
                    },
                    {
                        "id": 1,
                        "content": "Comment 1",
                        "author": {
                            "id": 2,
                            "username": "itachi"
                        }
                    }
                ]
            }

            """));
    }

    private void expectElementToMatch(ResultActions resultActions, long id, String title, String content, String author, String createdAt) throws Exception {
        resultActions.andExpect(jsonPath("$[0].title").value(title))
            .andExpect(jsonPath("$[0].content").value(content))
            .andExpect(jsonPath("$[0].author").value(author))
            .andExpect(jsonPath("$[0].createdAt").value(createdAt))
            .andExpect(jsonPath("$[0].id").value(id));
    }
}
