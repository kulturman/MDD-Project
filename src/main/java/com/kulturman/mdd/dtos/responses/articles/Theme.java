package com.kulturman.mdd.dtos.responses.articles;

public class Theme {
    public final Long id;
    public final String name;

    public Theme(com.kulturman.mdd.entities.Theme theme) {
        id = theme.getId();
        name = theme.getName();
    }
}
