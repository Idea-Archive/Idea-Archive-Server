package Idea.Archive.IdeaArchive.domain.application.repository;

import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application,Long> {

    void deleteByPostAndMember(Post post, Member member);
    Boolean existsByPostAndMember(Post post, Member member);
    List<Application> findByPost(Post post);
}
