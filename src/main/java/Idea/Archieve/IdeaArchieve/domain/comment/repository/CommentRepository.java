package Idea.Archieve.IdeaArchieve.domain.comment.repository;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
