package com.kulturman.mdd.dtos.responses.articles;

public class Comment {
    public Long id;
    public String content;
    public Author author;
    public Comment(com.kulturman.mdd.entities.Comment comment) {
        id = comment.getId();
        content = comment.getContent();
        author = new Author(comment.getAuthor());
    }
}
