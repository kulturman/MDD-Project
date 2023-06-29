package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.responses.ArticleResponse;
import com.kulturman.mdd.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAll() {
        var articles = articleService.getAll();
        return ResponseEntity.ok(ArticleResponse.mapFromEntities(articles));
    }
}
