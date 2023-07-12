package com.kulturman.mdd.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CreateArticleRequest(
    @NotNull
    Long themeId,
    @NotEmpty
    String title,
    @NotEmpty
    String content
) {
}
