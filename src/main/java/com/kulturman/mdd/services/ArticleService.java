package com.kulturman.mdd.services;

import com.kulturman.mdd.entities.Article;
import com.kulturman.mdd.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAll(String order) {
        var sortBy = order.equals("ASC") ? Sort.by("createdAt").ascending(): Sort.by("createdAt").descending();
        return articleRepository.findAll(sortBy);
    }
}
