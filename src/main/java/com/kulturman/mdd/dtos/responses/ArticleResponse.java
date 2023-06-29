package com.kulturman.mdd.dtos.responses;

import com.kulturman.mdd.entities.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ArticleResponse(
    String title, String content,
    String author, LocalDateTime createdAt
    ) {

    public static List<ArticleResponse> mapFromEntities(List<Article> articles) {
        return articles.stream().map(article -> new ArticleResponse(
            article.getTitle(),
            article.getContent(),
            article.getAuthor().username(),
            article.getCreatedAt()
        )).collect(Collectors.toList());
    }
}