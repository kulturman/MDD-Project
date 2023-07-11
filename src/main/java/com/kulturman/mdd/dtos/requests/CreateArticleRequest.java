package com.kulturman.mdd.dtos.requests;

public record CreateArticleRequest(long themeId, String title, String content) {
}
