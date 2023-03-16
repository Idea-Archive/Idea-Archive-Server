package Idea.Archieve.IdeaArchieve.domain.post.repository;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsHeartByMemberAndPost(Member member , Post post);

    void deleteHeartByMemberAndPost(Member member, Post post);
    void deleteByPost(Post post);

    void deleteByMember_MemberId(Long memberId);
    List<Heart> findByMember_MemberId(Long memberId);
}