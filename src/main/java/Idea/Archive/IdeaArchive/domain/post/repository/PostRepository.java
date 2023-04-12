package Idea.Archive.IdeaArchive.domain.post.repository;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.category.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(@Param("keyword") String keyword);
    @Query("SELECT p FROM Post p JOIN p.category c WHERE c IN (:category) GROUP BY p HAVING COUNT(DISTINCT c) = :numCategory")
    List<Post> findByAllCategories(@Param("category") List<Category> category, @Param("numCategory") Long numCategory);
    void deleteByMember_MemberId(Long memberId);
    @Query("SELECT p FROM Post p ORDER BY p.heartCount + p.views DESC")
    List<Post> findAllOrderByHeartCountPlusViesDesc();
    List<Post> findByMember(Member member);
    @Query("SELECT COUNT(*) = 0 " +
            "FROM Post p " +
            "WHERE p NOT IN :posts OR p.member <> :member")
    boolean isAllMembersEqual(@Param("member") Member member, @Param("posts") List<Post> posts);

}
