package Idea.Archive.IdeaArchive.domain.post.repository;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewByCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<ViewByCategoryResponse> findByTitleContainingAndCategory(String searchKeyword, String category);
    List<ViewByCategoryResponse> findByTitleContaining(String searchKeyword);
    List<ViewByCategoryResponse> findByCategory(String category);
    void deleteByMember_MemberId(Long memberId);
    @Query("SELECT p FROM Post p ORDER BY p.heartCount + p.views DESC")
    List<Post> findAllOrderByHeartCountPlusViesDesc();
    List<Post> findByMember(Member member);
    @Query("SELECT COUNT(*) = 0 " +
            "FROM Post p " +
            "WHERE p NOT IN :posts OR p.member <> :member")
    boolean isAllMembersEqual(@Param("member") Member member, @Param("posts") List<Post> posts);

}
