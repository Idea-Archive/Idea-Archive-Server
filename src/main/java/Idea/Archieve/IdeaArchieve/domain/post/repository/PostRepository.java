package Idea.Archieve.IdeaArchieve.domain.post.repository;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingAndCategory(String searchKeyword, String category);
    List<Post> findByTitleContaining(String searchKeyword);
    List<Post> findByCategory(String category);
}
