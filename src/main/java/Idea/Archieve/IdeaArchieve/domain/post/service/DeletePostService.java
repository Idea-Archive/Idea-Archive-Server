package Idea.Archieve.IdeaArchieve.domain.post.service;

<<<<<<< HEAD
import Idea.Archieve.IdeaArchieve.domain.post.entity.Post;
=======
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
>>>>>>> f5fd92affad7047fd0a52f67e99faddc67029615
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.domain.post.repository.HeartRepository;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;
    private final HeartRepository heartRepository;

    private void verifyPostWriter(Post post) {
        if (!memberUtil.currentMember().equals(post.getMember())) {
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
        }
    }

    @Transactional
    public void execute(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시글입니다."));
        verifyPostWriter(post);
        heartRepository.deleteByPost(post);
        postRepository.deleteById(postId);
    }
}
