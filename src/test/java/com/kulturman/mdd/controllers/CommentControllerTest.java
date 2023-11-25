package com.kulturman.mdd.controllers;

import com.kulturman.mdd.BaseIntegrationTest;
import com.kulturman.mdd.dtos.requests.CreateCommentRequest;
import com.kulturman.mdd.dtos.responses.CreatedResourceId;
import com.kulturman.mdd.dtos.responses.comments.NewCommentResponse;
import com.kulturman.mdd.entities.Comment;
import com.kulturman.mdd.repositories.CommentRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends BaseIntegrationTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void comment() throws Exception {
        long articleId = 2;
        long userId = 2;
        final var commentContent = "Dummy comment";

        var response = mockMvc.perform(
                authenticatedPost(String.format("/api/articles/%d/comment", articleId))
                    .content(objectMapper.writeValueAsString(new CreateCommentRequest(commentContent)))
            )
            .andExpect(status().is(201))
            .andReturn().getResponse().getContentAsString();

        var result = objectMapper.readValue(response, NewCommentResponse.class);
        var comment = commentRepository.findById(result.id()).orElseThrow();

        assertEquals(userId, result.userId());
        assertEquals("itachi", result.username());
        commentMatches(comment, commentContent, userId, articleId);
    }

    void commentMatches(Comment comment, String expectedText, long userId, long articleId) {
        assertEquals(articleId, comment.getArticle().getId());
        assertEquals(expectedText, comment.getContent());
        assertEquals(userId, comment.getAuthor().getId());
    }
}
