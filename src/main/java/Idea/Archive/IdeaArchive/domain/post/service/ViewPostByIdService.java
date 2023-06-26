package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response.ViewCommentByPostResponse;
import Idea.Archive.IdeaArchive.domain.comment.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    @Transactional(rollbackFor = Exception.class)
    public ViewPostByIdResponse execute(Long PostId) {
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new NotExistPostException());
        List<Comment> comments = commentRepository.findByPost(post);
        List<ViewCommentByPostResponse> comment = ViewCommentByPostResponse.convertToCommentList(comments);
        post.updateViews(post.getViews() + 1);
        postRepository.save(post);
        return ViewPostByIdResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .member(ViewMemberResponse.convertToMember(post.getMember()))
                .comment(comment)
                .heartCount(post.getHeartCount())
                .commentCount(post.getCommentCount())
                .applicantCount(post.getApplicantCount())
                .views(post.getViews())
                .createdDate(post.getCreatedDate())
                .heart(post.getHeart())
                .build();
    }
}
