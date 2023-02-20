package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotMyPost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    private void verifyPostWriter(Post post) {
        if (!memberUtil.currentMember().equals(post.getMember())) {
            System.out.println(memberUtil.currentMember());
            System.out.println(post.getMember());
            throw new NotMyPost("남의 글은 수정할 수 없습니다.");
        }
    }

    public void execute(Long postId, UpdatePost updatePost) {
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시글입니다."));
        verifyPostWriter(post);
        post.update(updatePost.getTitle(), updatePost.getContent(), updatePost.getCategory());
        postRepository.save(post);
    }

}
