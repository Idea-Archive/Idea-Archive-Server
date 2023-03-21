package Idea.Archive.IdeaArchive.domain.application.repository;

import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    void deleteByPostAndMember(Post post, Member member);
    Boolean existsByPostAndMember(Post post, Member member);
}
