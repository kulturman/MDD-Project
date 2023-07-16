package com.kulturman.mdd.dtos.responses;

import com.kulturman.mdd.entities.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
    long id, String title, String content,
    String author, LocalDateTime createdAt
    ) {

    public static List<ArticleResponse> mapFromEntities(List<Article> articles) {
        return articles.stream().map(article -> new ArticleResponse(
            article.getId(),
            article.getTitle(),
            article.getContent(),
            article.getAuthor().username(),
            article.getCreatedAt()
        )).toList();
    }
}
