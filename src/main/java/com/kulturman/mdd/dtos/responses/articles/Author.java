package com.kulturman.mdd.dtos.responses.articles;

import com.kulturman.mdd.entities.User;

public class Author {
    public Long id;
    public String username;

    public Author(User user) {
        id = user.getId();
        username = user.username();
    }
}
