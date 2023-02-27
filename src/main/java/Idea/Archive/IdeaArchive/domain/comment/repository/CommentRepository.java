package Idea.Archive.IdeaArchive.domain.comment.repository;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
