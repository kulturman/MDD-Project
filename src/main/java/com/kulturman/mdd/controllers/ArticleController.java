package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.requests.CreateArticleRequest;
import com.kulturman.mdd.dtos.responses.ArticleResponse;
import com.kulturman.mdd.dtos.responses.articles.getOne.GetOneArticleResponse;
import com.kulturman.mdd.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        articleService.create(createArticleRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOneArticleResponse> getOne(@PathVariable("id") long id) {
        return ResponseEntity.ok(articleService.findOne(id));
    }
}
