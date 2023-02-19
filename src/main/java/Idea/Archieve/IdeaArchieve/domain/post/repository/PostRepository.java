package Idea.Archieve.IdeaArchieve.domain.post.repository;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewByCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<ViewByCategoryResponse> findByTitleContainingAndCategory(String searchKeyword, String category);
    List<ViewByCategoryResponse> findByTitleContaining(String searchKeyword);
    List<ViewByCategoryResponse> findByCategory(String category);
}
