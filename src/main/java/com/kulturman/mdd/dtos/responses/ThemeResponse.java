package com.kulturman.mdd.dtos.responses;

import com.kulturman.mdd.entities.Theme;

import java.util.List;
import java.util.stream.Collectors;

public record ThemeResponse(long id, String name, String description) {
    public static List<ThemeResponse> mapFromEntities(List<Theme> themes) {
        return themes.stream().map(theme -> new ThemeResponse(
            theme.getId(),
            theme.getName(),
            theme.getDescription()
        )).collect(Collectors.toList());
    }
}
