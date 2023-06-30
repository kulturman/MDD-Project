package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.responses.ArticleResponse;
import com.kulturman.mdd.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAll(
        @RequestParam("sort") String order
    ) {
        var articles = articleService.getAll(order);
        return ResponseEntity.ok(ArticleResponse.mapFromEntities(articles));
    }
}
