package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewPostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ViewPostResponse> execute() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(p -> ViewPostResponse.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .category(p.getCategory())
                        .heartCount(p.getHeartCount())
                        .commentCount(p.getCommentCount())
                        .member(ViewMemberResponse.convertToMember(p.getMember()))
                        .createdDate(p.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
