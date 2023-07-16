package com.kulturman.mdd.dtos.responses.auth.me;


import com.kulturman.mdd.entities.Theme;

public class Subscription {
    public final Long id;
    public final String title;
    public final String description;

    public Subscription(Theme theme) {
        id = theme.getId();
        title = theme.getName();
        description = theme.getDescription();
    }
}
