package Idea.Archive.IdeaArchive.domain.member.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.post.entity.Post;
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
    private List<String> category;
    private String writer;

    public static MyPostResponse convertToPost(Post post) {
        return MyPostResponse.builder()
                .title(post.getTitle())
                .category(post.getCategory())
                .writer(post.getMember().getName())
                .build();
    }

    public static List<MyPostResponse> convertToPostList(List<Post> postList) {
        Stream<Post> stream = postList.stream();
        return stream.map(MyPostResponse::convertToPost)
                .collect(Collectors.toList());
    }
}
