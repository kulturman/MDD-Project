package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.requests.CreateCommentRequest;
import com.kulturman.mdd.dtos.responses.CreatedResourceId;
import com.kulturman.mdd.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/articles/{articleId}/comment")
    public ResponseEntity<CreatedResourceId> comment(
        @PathVariable("articleId") long articleId,
        @Valid @RequestBody CreateCommentRequest createCommentRequest
        ) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CreatedResourceId(commentService.createComment(articleId, createCommentRequest))
        );
    }
}
