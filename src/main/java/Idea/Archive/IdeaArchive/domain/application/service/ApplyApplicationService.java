package Idea.Archive.IdeaArchive.domain.application.service;

import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import Idea.Archive.IdeaArchive.domain.application.repository.ApplicationRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyApplicationService {

    private final ApplicationRepository applicationRepository;
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional
    public void execute(Long postId) {
        Member member = memberUtil.currentMember();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("게시글이 존재하지 않습니다."));
        if(applicationRepository.existsByPostAndMember(post,member)) {
            post.updateApplication(post.getApplicantCount()-1);
            applicationRepository.deleteByPostAndMember(post,member);
        } else {
            Application application = Application.builder()
                    .post(post)
                    .member(member)
                    .build();
            post.updateApplication(post.getApplicantCount() + 1);
            postRepository.save(post);
            applicationRepository.save(application);
        }
    }
}
