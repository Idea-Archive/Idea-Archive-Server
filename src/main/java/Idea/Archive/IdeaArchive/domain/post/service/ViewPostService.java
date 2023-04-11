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

    @Transactional(readOnly = true)
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
                        .member(ViewMemberResponse.convertToMember(n.getMember()))
                        .build())
                .collect(Collectors.toList());
    }
}
