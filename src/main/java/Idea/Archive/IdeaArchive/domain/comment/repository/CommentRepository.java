package Idea.Archive.IdeaArchive.domain.comment.repository;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
