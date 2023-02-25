package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response;

import Idea.Archieve.IdeaArchieve.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPostResponse {

    private String title;
    private String category;
    private String writer;

    public static MyPostResponse convertToComment(Post post) {
        return MyPostResponse.builder()
                .title(post.getTitle())
                .category(post.getCategory())
                .writer(post.getMember().getName())
                .build();
    }

    public static List<MyPostResponse> convertToCommentList(List<Post> commentList) {
        Stream<Post> stream = commentList.stream();
        return stream.map(MyPostResponse::convertToComment)
                .collect(Collectors.toList());
    }
}
