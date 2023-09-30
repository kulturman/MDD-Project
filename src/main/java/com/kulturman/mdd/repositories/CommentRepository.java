package com.kulturman.mdd.repositories;

import com.kulturman.mdd.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
