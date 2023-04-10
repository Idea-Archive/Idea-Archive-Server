package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.post.entity.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewByHeartListResponse {

    private String title;
    private Category category;

    public static ViewByHeartListResponse convertToHeart(Heart heart) {
        return ViewByHeartListResponse.builder()
                .title(heart.getPost().getTitle())
                .category(heart.getPost().getCategory().get(0))
                .build();
    }

    public static List<ViewByHeartListResponse> convertToHeartList(List<Heart> hearts) {
        Stream<Heart> stream = hearts.stream();
        return stream.map(ViewByHeartListResponse::convertToHeart).collect(Collectors.toList());
    }
}
