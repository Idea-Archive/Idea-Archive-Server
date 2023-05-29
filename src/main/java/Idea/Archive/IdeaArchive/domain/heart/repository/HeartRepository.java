package Idea.Archive.IdeaArchive.domain.heart.repository;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.heart.entity.Heart;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeartRepository extends CrudRepository<Heart, Long> {

    boolean existsHeartByMemberAndPost(Member member , Post post);
    void deleteHeartByMemberAndPost(Member member, Post post);
    void deleteByPost(Post post);
    void deleteByMember(Member member);
    List<Heart> findByMember(Member member);
}
