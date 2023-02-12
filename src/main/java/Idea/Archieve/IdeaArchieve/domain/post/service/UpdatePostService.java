package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    public void UpdatePost(Long postId, UpdatePost updatePost) {
        Post findPost = postRepository.findById(postId)
                        .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시글입니다."));
        checkUser(findPost);
        findPost.update(updatePost.getTitle(), updatePost.getContent(), updatePost.getCategory());
        postRepository.save(findPost);
    }

    public void checkUser(Post post) {
        if(!memberUtil.currentMember().equals(post.getMember()))
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
    }

}
