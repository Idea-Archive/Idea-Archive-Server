package Idea.Archive.IdeaArchive.domain.post.repository;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsHeartByMemberAndPost(Member member , Post post);
    void deleteHeartByMemberAndPost(Member member, Post post);
    void deleteByPost(Post post);
    void deleteByMember(Member member);
    List<Heart> findByMember_MemberId(Long memberId);
}
