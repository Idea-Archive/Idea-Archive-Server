package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewPostByIdService {

    private final PostRepository postRepository;

    public Post ViewPostById(Long PostId) {
        return postRepository.findById(PostId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시판입니다."));
    }

}
