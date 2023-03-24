package Idea.Archive.IdeaArchive.domain.post.service;


import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewPostByHeartService {

    private final PostRepository postRepository;

    @Transactional
    public List<ViewPostResponse> execute() {
        List<Post> posts = postRepository.findAllOrderByHeartCountPlusViesDesc();
        if(posts.isEmpty()) {
            throw new NotExistPostException("게시글이 존재하지 않습니다");
        }
        return posts.stream()
                .map(p -> ViewPostResponse.builder()
                        .id(p.getPostId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .category(p.getCategory())
                        .heartCount(p.getHeartCount())
                        .commentCount(p.getCommentCount())
                        .build())
                .collect(Collectors.toList());
    }
}
