package Idea.Archive.IdeaArchive.domain.post.repository;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.enums.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingOrContentContaining(@Param("title") String title, @Param("content") String content);
    @Query("SELECT p FROM Post p JOIN p.category c WHERE c IN (:category) GROUP BY p HAVING COUNT(DISTINCT c) = :numCategory")
    List<Post> findByAllCategories(@Param("category") List<Category> category, @Param("numCategory") long numCategory);
    void deleteByMember(Member member);
    @Query("SELECT p FROM Post p ORDER BY p.heartCount + p.views DESC")
    List<Post> findAllOrderByHeartCountPlusViesDesc();
    List<Post> findByMember(Member member);
}
