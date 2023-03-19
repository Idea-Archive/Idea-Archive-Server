package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewPostService {

    private final PostRepository postRepository;

    public List<ViewPostResponse> execute() {
        List<Post> noticeList = postRepository.findAll();
        return noticeList.stream()
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
