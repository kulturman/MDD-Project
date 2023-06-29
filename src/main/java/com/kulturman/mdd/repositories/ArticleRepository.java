package com.kulturman.mdd.repositories;

import com.kulturman.mdd.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
