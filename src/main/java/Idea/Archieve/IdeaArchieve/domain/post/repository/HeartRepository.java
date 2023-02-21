package Idea.Archieve.IdeaArchieve.domain.post.repository;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsHeartByMemberAndPost(Member member , Post post);
}
