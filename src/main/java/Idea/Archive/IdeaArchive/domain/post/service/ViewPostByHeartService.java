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
    public List<ViewPostResponse> execute(){
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC,"popularValue"));
        if(posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다");
        }
        return posts.stream()
                .map(n -> ViewPostResponse.builder()
                        .id(n.getPostId())
                        .title(n.getTitle())
                        .content(n.getContent())
                        .category(n.getCategory())
                        .heartCount(n.getHeartCount())
                        .commentCount(n.getCommentCount())
                        .build())
                .collect(Collectors.toList());
    }
}
