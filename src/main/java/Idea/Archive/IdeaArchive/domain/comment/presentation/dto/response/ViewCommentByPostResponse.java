package Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter @Builder
@AllArgsConstructor
public class ViewCommentByPostResponse {

    private Long commentId;
    private String content;
    @CreatedDate
    private LocalDateTime createdDate;
    private ViewMemberResponse member;

    public static ViewCommentByPostResponse convertToComment(Comment comment) {
        return ViewCommentByPostResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .member(ViewMemberResponse.convertToMember(comment.getMember()))
                .build();
    }

    public static List<ViewCommentByPostResponse> convertToCommentList(List<Comment> commentList) {
        Stream<Comment> stream = commentList.stream();
        return stream.map(ViewCommentByPostResponse::convertToComment)
                .collect(Collectors.toList());
    }

}
