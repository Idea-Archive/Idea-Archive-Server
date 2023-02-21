package Idea.Archieve.IdeaArchieve.domain.chat.repository;

import Idea.Archieve.IdeaArchieve.domain.chat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
