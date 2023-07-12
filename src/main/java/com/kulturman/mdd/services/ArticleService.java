package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.requests.CreateArticleRequest;
import com.kulturman.mdd.entities.Article;
import com.kulturman.mdd.entities.User;
import com.kulturman.mdd.repositories.ArticleRepository;
import com.kulturman.mdd.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;

    public List<Article> getAll(String order) {
        var sortBy = order.equals("ASC") ? Sort.by("createdAt").ascending(): Sort.by("createdAt").descending();
        return articleRepository.findAll(sortBy);
    }

    public void create(CreateArticleRequest createArticleRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(createArticleRequest, article);
        article.setCreatedAt(LocalDateTime.now());
        article.setTheme(themeRepository.getReferenceById(createArticleRequest.themeId()));
        article.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        articleRepository.save(article);
    }
}
