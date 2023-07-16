package com.kulturman.mdd.dtos.responses.articles;

public class Theme {
    public Long id;
    public String name;

    public Theme(com.kulturman.mdd.entities.Theme theme) {
        id = theme.getId();
        name = theme.getName();
    }
}
