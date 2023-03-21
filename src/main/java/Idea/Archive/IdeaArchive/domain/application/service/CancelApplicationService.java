package Idea.Archive.IdeaArchive.domain.application.service;

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
public class CancelApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MemberUtil memberUtil;
    private final PostRepository postRepository;

    @Transactional
    public void execute(Long postId){
        Member member = memberUtil.currentMember();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("게시글이 존재하지 않습니다"));
        post.updateApplication(post.getApplicationCount()-1);
        postRepository.save(post);
        applicationRepository.deleteByPostAndMember(post,member);
    }
}
