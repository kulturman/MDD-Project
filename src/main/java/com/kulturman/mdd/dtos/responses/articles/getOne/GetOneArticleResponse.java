package com.kulturman.mdd.dtos.responses.articles.getOne;

import com.kulturman.mdd.dtos.responses.articles.Author;
import com.kulturman.mdd.dtos.responses.articles.Comment;
import com.kulturman.mdd.dtos.responses.articles.Theme;
import com.kulturman.mdd.entities.Article;

import java.time.LocalDateTime;
import java.util.List;

public class GetOneArticleResponse {
    public Long id;
    public LocalDateTime createdAt;
    public String title;
    public String content;
    public Theme theme;
    public Author author;
    public List<Comment> comments;
    public GetOneArticleResponse(Article article) {
        id = article.getId();
        createdAt = article.getCreatedAt();
        title = article.getTitle();
        content = article.getContent();
        author = new Author(article.getAuthor());
        comments = article.getComments().stream().map(Comment::new).toList();
        theme = new Theme(article.getTheme());
    }
}
