package com.kulturman.mdd.dtos.responses.articles;

import com.kulturman.mdd.entities.User;

public class Author {
    public final Long id;
    public final String username;

    public Author(User user) {
        id = user.getId();
        username = user.username();
    }
}
