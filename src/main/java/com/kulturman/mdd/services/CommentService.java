package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.requests.CreateCommentRequest;
import com.kulturman.mdd.entities.Comment;
import com.kulturman.mdd.entities.User;
import com.kulturman.mdd.repositories.ArticleRepository;
import com.kulturman.mdd.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public long createComment(long articleId, CreateCommentRequest createCommentRequest) {
        var comment = new Comment();
        comment.setArticle(articleRepository.findById(articleId).orElseThrow());
        comment.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        comment.setContent(createCommentRequest.content());

        return commentRepository.save(comment).getId();
    }
}
