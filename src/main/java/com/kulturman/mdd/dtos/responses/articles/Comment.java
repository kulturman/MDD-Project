package com.kulturman.mdd.dtos.responses.articles;

public class Comment {
    public final Long id;
    public final String content;
    public final Author author;
    public Comment(com.kulturman.mdd.entities.Comment comment) {
        id = comment.getId();
        content = comment.getContent();
        author = new Author(comment.getAuthor());
    }
}
