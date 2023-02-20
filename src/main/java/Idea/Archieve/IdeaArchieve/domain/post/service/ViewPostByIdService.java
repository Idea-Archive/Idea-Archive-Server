package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewPostByIdResponse;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewPostByIdService {

    private final PostRepository postRepository;

    public ViewPostByIdResponse execute(Long PostId) {
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시판입니다."));
        return ViewPostByIdResponse.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .build();
    }

}
