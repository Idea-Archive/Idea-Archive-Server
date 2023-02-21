package Idea.Archieve.IdeaArchieve.domain.comment.presentation.dto.response;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewPostByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewCommentByPostResponse {

    private String content;

    public static ViewCommentByPostResponse convertToCommentDto(Comment comment) {
        return ViewCommentByPostResponse.builder()
                .content(comment.getContent())
                .build();
    }

    public static List<ViewCommentByPostResponse> convertToCommentDtoList(List<Comment> commentList) {
        Stream<Comment> stream = commentList.stream();

        return stream.map(ViewCommentByPostResponse::convertToCommentDto).collect(Collectors.toList());

    }
}
