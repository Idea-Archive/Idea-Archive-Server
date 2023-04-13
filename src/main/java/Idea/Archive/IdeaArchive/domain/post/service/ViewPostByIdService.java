package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response.ViewCommentByPostResponse;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostByIdResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewPostByIdService {

    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public ViewPostByIdResponse execute(Long PostId) {
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시판입니다."));
        List<ViewCommentByPostResponse> comment = ViewCommentByPostResponse.convertToCommentList(post.getCommentList());
        post.updateViews(post.getViews()+1);
        postRepository.save(post);
        return ViewPostByIdResponse.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .member(ViewMemberResponse.convertToMember(post.getMember()))
                .comment(comment)
                .heartCount(post.getHeartCount())
                .commentCount(post.getCommentCount())
                .applicantCount(post.getApplicantCount())
                .views(post.getViews())
                .time(post.getTime())
                .build();
    }

}
